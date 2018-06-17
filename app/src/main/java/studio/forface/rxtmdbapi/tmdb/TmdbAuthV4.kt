@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package studio.forface.rxtmdbapi.tmdb

import android.content.Context
import android.content.Intent
import com.google.gson.annotations.SerializedName
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import okhttp3.ResponseBody
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import studio.forface.rxtmdbapi.utils.AuthActivity
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.now

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

internal typealias OnTokenListener = (TokenV4) -> Unit
private const val TOKEN_AUTHENTICATION_REQUEST_URL = "https://www.themoviedb.org/auth/access?request_token=\$TOKEN"
class TmdbAuthV4(
        internal val iTmdbAuth: ITmdbAuthV4,
        private val onTokenListener: OnTokenListener
) {

    companion object {
        val tokenAuthorizationSubject = PublishSubject.create<Boolean>()
    }

    private var token : TokenV4? = null

    /**
     * It creates a [Token] and cache it in [token].
     * @see ITmdbAuthV4.createToken .
     * @return a [Completable]
     */
    fun preloadToken() : Completable = iTmdbAuth.createToken( AuthActivity.URI_V4 )
            .doOnSuccess { token = it }
            .ignoreElement()

    /**
     * The return value is used just for store the [TokenV4] in the device and use it for next
     * application session. It will be automatically set in [TmdbApi].
     *
     * If [token] is cached (not null) and not [Token.expired] use it, else create a new one
     * @see ITmdbAuthV4.createToken .
     * Then it sends the user to the Tmdb web page for authenticate the [Token].
     * @see ITmdbAuthV4.getTokenValidationUrl .
     * Then authorize the just created [TokenV4].
     * @see authenticate .
     * @see ITmdbAuthV4.createAccessToken .
     * Then notify the [TmdbApi] with the just created session thought [OnTokenListener], which will
     * add it as [TmdbInterceptor.headers].
     *
     * @param context the Android [Context] required for the [Intent].
     * @return a [Single] of [TokenV4]
     */
    fun authenticate( context: Context ) : Single<TokenV4> {
        return let {
            if ( token?.expired == false ) Single.just( token!! )
            else iTmdbAuth.createToken( AuthActivity.URI_V4 )
        }
                .doOnSuccess {
                    val url = iTmdbAuth.getTokenValidationUrl( it )
                    val intent = Intent( context, AuthActivity::class.java )
                            .putExtra( AuthActivity.PARAM_AUTH_URL, url )
                    context.startActivity( intent )
                }
                .flatMap { token -> tokenAuthorizationSubject.map { token }.firstOrError() }
                .flatMap { authenticate( it ) }
    }

    /**
     * Authorize the [TokenV4].
     * @see authenticate .
     * @see ITmdbAuthV4.createAccessToken .
     * Then notify the [TmdbApi] with the just created session thought [OnTokenListener], which will
     * add it as [TmdbInterceptor.headers].
     *
     * @param token the [TokenV4] to authenticate.
     * @return a [Single] of [TokenV4]
     */
    fun authenticate( token: TokenV4 ): Single<TokenV4> = iTmdbAuth.createAccessToken( token )
                .doOnSuccess { onTokenListener( it ) }

    /**
     * It will notify [TmdbApi] to remove the Header from the [TmdbApi.interceptor] creating a
     * dummy [TokenV4].
     * @return a [Single] of [ResponseBody].
     */
    fun logout(): Single<ResponseBody> = iTmdbAuth.deleteAccessToken()
            .doAfterSuccess { onTokenListener( TokenV4( EMPTY_STRING ) ) }

}

private const val BASE_PATH = "/4/auth"
interface ITmdbAuthV4 {

    /**
     * This method generates a new request token that you can ask a user to approve. This is the
     * first step in getting permission from a user to read and write data on their behalf. You can
     * read more about this system here:
     * https://developers.themoviedb.org/4/auth/user-authorization-1 .
     *
     * Note that there is an optional body you can post alongside this request to set a redirect
     * URL or callback that will be executed once a request token has been approved on TMDb.
     * @param uriString is the uri to redirect after the result.
     * @return a [Single] of [TokenV4].
     */
    @POST("$BASE_PATH/request_token")
    @FormUrlEncoded
    fun createToken(
            @Field("redirect_to")  uriString: String? = null
    ) : Single<TokenV4>

    /**
     * This method will finish the user authentication flow and issue an official user access
     * token. The request token in this request is sent along as part of the POST body. You should
     * still use your standard API read access token for authenticating this request.
     * @param token must has been validated.
     * @return a [Single] of [TokenV4].
     */
    @POST("$BASE_PATH/access_token")
    @FormUrlEncoded
    fun createAccessToken(
            @Field("request_token") token: TokenV4
    ) : Single<TokenV4>

    @POST("$BASE_PATH/access_token")
    @FormUrlEncoded
    fun createRawAccessToken(
            @Field("request_token") token: TokenV4
    ) : Single<ResponseBody>

    /**
     * This method gives your users the ability to log out of a session.
     * @return a [Single] of [ResponseBody].
     */
    @DELETE("$BASE_PATH/access_token")
    fun deleteAccessToken() : Single<ResponseBody>

}

/**
 * Should be used it to open a web page where a logged user on Tmdb website can authorize the [Token].
 * Once the user has approved your request token, they will either be redirected to the URL you
 * specified in the redirect_to parameter or to the /authenticate/allow path on TMDb. If they
 * aren't redirected to a custom URL, the page will also have a Authentication-Callback header.
 * This header contains the API call for step #3. You can either manually generate it or simply
 * use the one we return.
 * @param token the [Token] to authorize.
 * @return a [String] with the url.
 */
internal fun ITmdbAuthV4.getTokenValidationUrl( token: TokenV4 ) =
        TOKEN_AUTHENTICATION_REQUEST_URL
                .replace("\$TOKEN", token.value )


@Suppress("MemberVisibilityCanBePrivate")
data class TokenV4(
    @SerializedName("request_token")        val _value1: String,
    @SerializedName("access_token")         val _value2: String? = null,
    @SerializedName("account_id")           val accountId: String? = null
) {
    val value       get() = _value2 ?: _value1
    val expiration  get() = now + 15 * 60 * 1000 // 15 mins later.
    val expired     get() = expiration < now

    @Deprecated(
            "toString() has been override for Api purpose",
            ReplaceWith("fullString()")
    )
    override fun toString() = value
    fun fullString() = "{ value= \"$value\", accountId= \"$accountId\" }"
}