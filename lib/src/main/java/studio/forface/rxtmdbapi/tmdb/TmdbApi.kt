@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package studio.forface.rxtmdbapi.tmdb

import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import studio.forface.rxtmdbapi.models.Media
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.MediaDeserializer
import studio.forface.rxtmdbapi.utils.MediaSerializer
import retrofit2.Response as RetrofitResponse

private const val TMDB_API_URL = "https://api.themoviedb.org/"
private const val TMDB_API_URL_V3 = "${TMDB_API_URL}3/"
private const val TMDB_API_URL_V4 = "${TMDB_API_URL}4/"

private const val PARAM_API_KEY = "api_key"
private const val HEADER_API_V4_ACCESS_TOKEN = "Authorization"
private const val PARAM_ACCOUNT_ID = "account_id"
private const val PARAM_SESSION_ID = "session_id"
private const val PARAM_GUEST_SESSION_ID = "guest_session_id"

internal const val HEADER_JSON = "Content-Type: application/json;charset=utf-8"

typealias OnNewGuestSession = (Session) -> Unit

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
class TmdbApi(
        apiV3Key: String,
        apiV4accessToken: String? = null,
        enableLogging: Boolean = false
) {

    val settings = TmdbApiConfig
    var onNewGuestSession: OnNewGuestSession = {}

    fun setSession( sessionId: String, guest: Boolean = false ) {
        setSession( Session( sessionId, guest ) )
    }
    fun setSession( session: Session ) {
        val guest = session is Session.Guest || session.guest
        interceptor.removeQueryParams( PARAM_SESSION_ID )
        interceptor.removeQueryParams( PARAM_GUEST_SESSION_ID )

        if ( session.success ) {
            val paramName = when( guest ) {
                false -> PARAM_SESSION_ID
                true ->  {
                    onNewGuestSession( session )
                    PARAM_GUEST_SESSION_ID
                }
            }
            interceptor.addQueryParams(paramName to session.sessionId)
        }
    }

    internal var tokenV4: TokenV4? = null
    fun setAccessToken( token: String, accountId: String ) {
        setAccessToken( TokenV4( token, accountId = accountId ) )
    }
    fun setAccessToken( token: TokenV4 ) {
        if ( token.value.isNotBlank() ) {
            tokenV4 = token
            interceptor.run {
                addHeaders(HEADER_API_V4_ACCESS_TOKEN to "Bearer ${token.value}")
                addQueryParams(PARAM_ACCOUNT_ID to token.accountId!! )
            }
        } else {
            tokenV4 = null
            interceptor.run {
                removeHeaders( HEADER_API_V4_ACCESS_TOKEN )
                removeQueryParams( PARAM_ACCOUNT_ID )
            }
        }
    }

    private val interceptor = TmdbInterceptor().apply {
        addQueryParams(PARAM_API_KEY to apiV3Key )
        apiV4accessToken?.let {
            if ( it.split('.' ).size == 3 ) {
                addHeaders(HEADER_API_V4_ACCESS_TOKEN to "Bearer $it" )
            } else {
                throw IllegalArgumentException( "Api V4 access read token has a wrong format!" )
            }
        }
    }

    private val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    private val httpClientBuilder = OkHttpClient.Builder()
            .authenticator( TmdbAuthenticator( this ) )
            .addInterceptor( interceptor )
            .apply { if ( enableLogging ) addInterceptor( loggingInterceptor ) }

    private val gson get() = GsonBuilder()
            .registerTypeAdapter( Media::class.java, MediaSerializer() )
            .registerTypeAdapter( Media::class.java, MediaDeserializer() )
            .create()

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl( TMDB_API_URL_V3 )
            .addCallAdapterFactory( RxJava2CallAdapterFactory.createAsync() )
            .addConverterFactory( GsonConverterFactory.create( gson ) )

    private inline fun <reified S> getService() : S = retrofitBuilder
            .client( httpClientBuilder.build() )
            .build()
            .create( S::class.java )


    val auth            by lazy { TmdbAuth( getService() ) { setSession( it ) } }
    val authV4          by lazy { TmdbAuthV4( getService() ) { setAccessToken( it ) } }
    val account         by lazy { getService<TmdbAccount>() }
    val accountV4       by lazy { getService<TmdbAccountV4>() }
    val certifications  by lazy { getService<TmdbCertifications>() }
    val changes         by lazy { getService<TmdbChanges>() }
    val collections     by lazy { getService<TmdbCollections>() }
    val companies       by lazy { getService<TmdbCompanies>() }
    val config          by lazy { getService<TmdbConfig>() }
    val credits         by lazy { getService<TmdbCredits>() }
    val discover        by lazy { getService<TmdbDiscover>() }
    val guest           by lazy { getService<TmdbGuest>() }
    val keywords        by lazy { getService<TmdbKeywords>() }
    val lists           by lazy { getService<TmdbLists>() }
    val listsV4         by lazy { getService<TmdbListsV4>() }
    val movies          by lazy { getService<RxTmdbMovies>() }
    val networks        by lazy { getService<TmdbNetworks>() }
    val people          by lazy { getService<TmdbPeople>() }
    val reviews         by lazy { getService<TmdbReviews>() }
    val search          by lazy { getService<TmdbSearch>() }
    val tvShows         by lazy { getService<TmdbTvShows>() }
    val tvSeasons       by lazy { getService<TmdbTvSeasons>() }
    val tvEpisodes      by lazy { getService<TmdbTvEpisodes>() }

}

private class TmdbInterceptor : Interceptor {

    private val headers = mutableMapOf<String, String>()
    private val params =  mutableMapOf<String, String>()

    internal fun addHeaders( vararg headers: Pair<String, String> ) {
        this.headers.putAll( headers )
    }

    internal fun removeHeaders( vararg names: String ) {
        names.forEach { headers.remove( it ) }
    }

    internal fun addQueryParams( vararg queryParams: Pair<String, String> ) {
        params.putAll( queryParams )
    }

    internal fun removeQueryParams( vararg keys: String ) {
        keys.forEach { params.remove( it ) }
    }

    override fun intercept( chain: Interceptor.Chain ): Response {
        val request = chain.request()
        val url = request.url()

        var sessionId: String = EMPTY_STRING
        var sessionIndex = -1
        params[PARAM_GUEST_SESSION_ID]?.let {
            sessionId = it
            sessionIndex = url.pathSegments().indexOfFirst { it == PATH_GUEST_SESSION_ID }
        }

        var accountId: String = EMPTY_STRING
        var accountIndex = -1
        params[PARAM_ACCOUNT_ID]?.let {
            accountId = it
            accountIndex = url.pathSegments().indexOfFirst { it == PATH_ACCOUNT_ID }
        }

        val finalUrl = url.newBuilder().apply {
            params.forEach { addQueryParameter( it.key, it.value ) }
            if ( sessionIndex > -1 ) setPathSegment( sessionIndex, sessionId )
            if ( accountIndex > -1 ) setPathSegment( accountIndex, accountId )
        }.build()

        println( finalUrl.toString() )

        val newRequest = request.newBuilder()
                .url( finalUrl )
                .headers( Headers.of( headers ) )
                .method( request.method(), request.body() )
                .build()

        return  chain.proceed( newRequest )
    }
}

private const val PATH_GUEST_SESSION = "guest_session"
private const val PATH_ACCOUNT = "account"
private class TmdbAuthenticator( val api: TmdbApi ): Authenticator {

    override fun authenticate( route: Route, response: Response ): Request? {
        var needToRequest = false

        val request = response.request()
        val url = request.url()
        val pathSegments = url.pathSegments()

        val requestBuilder = request.newBuilder().method( request.method(), request.body() )
        val urlBuilder = url.newBuilder()

        val guestIndex = pathSegments.indexOf( PATH_GUEST_SESSION )
        val accountIndex = pathSegments.indexOf( PATH_ACCOUNT )

        if ( guestIndex != -1 ) {
            val sessionId = authenticateGuest()
            urlBuilder.setPathSegment(guestIndex+1, sessionId )
            needToRequest = true
        }

        if ( accountIndex != -1 && pathSegments[accountIndex-1] != "0" ) {
            val token = authenticateV4()
            token?.let {
                urlBuilder.setPathSegment(accountIndex+1, it.accountId!! )
                requestBuilder.addHeader( HEADER_API_V4_ACCESS_TOKEN, "Bearer $it" )
                needToRequest = true
            }
        }

        return if ( needToRequest ) requestBuilder.url( urlBuilder.build() ).build()
        else null
    }

    private fun authenticateGuest() = api.auth.createGuessSession()
            .blockingGet().sessionId

    private fun authenticateV4() = api.tokenV4
            ?.let { api.authV4.authenticate( it ).blockingGet() }
}