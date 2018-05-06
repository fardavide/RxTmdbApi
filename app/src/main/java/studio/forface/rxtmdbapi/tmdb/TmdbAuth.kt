@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import com.google.gson.annotations.SerializedName
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.now
import studio.forface.rxtmdbapi.utils.timeInMillis

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

internal typealias OnSessionListener = ( Session ) -> Unit
class TmdbAuth(
        private val iTmdbAuth: ITmdbAuth,
        private val onSessionListener: OnSessionListener
) {

    private var token : Token? = null

    /**
     * It creates a [Token] and cache it in [token].
     * @see ITmdbAuth.createToken .
     * @return a [Completable]
     */
    fun preloadToken() : Completable = iTmdbAuth.createToken()
            .doOnSuccess { token = it }
            .ignoreElement()

    /**
     * The return value is used just for store the session in the device and use it for next
     * application session. It will be automatically set in [TmdbApi].
     *
     * If [token] is cached (not null) and not [Token.expired] use it, else create a new one
     * @see ITmdbAuth.createToken .
     * Then it validates the [Token] with user credentials
     * @see ITmdbAuth.validateTokenWithLogin .
     * Then creates a [Session] with the created [Token]
     * @see ITmdbAuth.createUserSession .
     * Then notify the [TmdbApi] with the just created session thought [onSessionListener], which will
     * add it as [QueryInterceptor.params].
     *
     * @param username the username of the User.
     * @param password the password of the User.
     * @return a [Single] of [Session.User]
     */
    fun createUserSessionWithLogin( username: String, password: String ) : Single<Session.User> {
        return let {
            if ( token?.expired == false ) Single.just(token!!)
            else iTmdbAuth.createToken()
        }
                .flatMap { iTmdbAuth.validateTokenWithLogin(username, password, it) }
                .flatMap { iTmdbAuth.createUserSession(it) }
                .doOnSuccess { onSessionListener(it) }
    }

    /**
     * The return value is used just for store the session in the device and use it for next
     * application session. It will be automatically set in [TmdbApi].
     *
     * It creates a guest [Session]
     * @see ITmdbAuth.createGuestSession .
     * Then notify the [TmdbApi] with the just created session thought [onSessionListener], which will
     * add it as [QueryInterceptor.params].
     *
     * @return a [Single] of [Session.Guest]
     */
    fun createGuessSession() : Single<Session.Guest> = iTmdbAuth.createGuestSession()
            .doOnSuccess { onSessionListener(it) }

    /**
     * It will notify [TmdbApi] to remove the [Session] from the [TmdbApi.interceptor] creating a
     * dummy [Session].
     */
    fun logout() {
        onSessionListener( Session.User(false, EMPTY_STRING) )
    }
}

private const val BASE_PATH = "authentication"
interface ITmdbAuth {

    /**
     * Create a temporary request token that can be used to validate a TMDb user login.
     * More details about how this works can be found here:
     * https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id .
     * @return a [Single] of [Token].
     */
    @GET("$BASE_PATH/token/new")
    fun createToken() : Single<Token>

    /**
     * You can use this method to create a fully valid session ID once a user has validated the
     * request token.
     * More information about how this works can be found here:
     * https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id .
     * @param token must has been validated.
     * @see getTokenValidationUrl or
     * @see validateTokenWithLogin .
     * @return a [Single] of [Session.User].
     */
    @GET("$BASE_PATH/session/new")
    fun createUserSession(
            @Query("request_token") token: Token
    ) : Single<Session.User>

    /**
     * This method allows an application to validate a request token by entering a username and
     * password.
     *
     * CAUTION.
     * Please note, using this method is strongly discouraged. The preferred method of validating a
     * request token is to have a user authenticate the request via the TMDb website.
     * You can read about that method here
     * https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id .
     * @param username the username of the user.
     * @param password the password of the user.
     * @param token the [Token] that needs to be authenticate.
     * @return a [Single] of authenticated [Token].
     */
    @GET("$BASE_PATH/token/validate_with_login")
    fun validateTokenWithLogin(
            @Query("username")      username: String,
            @Query("password")      password: String,
            @Query("request_token") token: Token
    ) : Single<Token>

    /**
     * This method will let you create a new guest session. Guest sessions are a type of session
     * that will let a user rate movies and TV shows but not require them to have a TMDb user account.
     * More information about user authentication can be found here:
     * https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id .
     *
     * Please note, you should only generate a single guest session per user (or device) as you will
     * be able to attach the ratings to a TMDb user account in the future. There is also IP limits
     * in place so you should always make sure it's the end user doing the guest session actions.
     *
     * If a guest session is not used for the first time within 24 hours, it will be automatically
     * deleted.
     * @return a [Single] of [Session.Guest].
     */
    @GET("$BASE_PATH/guest_session/new")
    fun createGuestSession() : Single<Session.Guest>

}

/**
 * Should be used it to open a web page where a logged user on Tmdb website can authorize the [Token].
 * Once the user has approved your request token, they will either be redirected to the URL you
 * specified in the redirect_to parameter or to the /authenticate/allow path on TMDb. If they
 * aren't redirected to a custom URL, the page will also have a Authentication-Callback header.
 * This header contains the API call for step #3. You can either manually generate it or simply
 * use the one we return.
 * @param token the [Token] to authorize.
 * @param redirectTo
 * @return a [String] with the url.
 */
// TODO:
internal fun ITmdbAuth.getTokenValidationUrl(token: Token, redirectTo: String? = null) =
        "https://www.themoviedb.org/authenticate/${token.value}" +
                (redirectTo?.let { "?redirect_to=$it" } ?: EMPTY_STRING)



data class Token(
        @SerializedName("request_token")        val value: String,
        @SerializedName("expires_at") private   val _expiration: String
) {
    val expiration get() = _expiration.timeInMillis
    val expired get() = expiration < now

    override fun toString() = value
}

sealed class Session {

    abstract val id: String
    abstract val success: Boolean
    override fun toString() = id

    data class User internal constructor(
            @SerializedName("success") override val success: Boolean,
            @SerializedName("session_id") override val id: String
    ) : Session()

    data class Guest internal constructor(
            @SerializedName("success") override val success: Boolean,
            @SerializedName("guest_session_id") override val id: String,
            @SerializedName("expires_at") private val _expiration: String
    ) : Session() {
        val expiration get() = _expiration.timeInMillis
    }

}