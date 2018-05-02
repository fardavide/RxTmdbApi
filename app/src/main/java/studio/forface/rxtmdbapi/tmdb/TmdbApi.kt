@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

// TODO: For test purpose only! To remove in production!
const val TMDB_API_KEY = "6328c07c1c982565d446d22aaa27a945"
const val TMDB_READ_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MzI4YzA3YzFjOTgyNTY1ZDQ0NmQyMmFhYTI3YTk0NSIsInN1YiI6IjU5MzFjZGIzYzNhMzY4NGYwMTAwMjRkOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.c3eoSAnqXxHv8604RVjPvcekdLN0IDPCzxMVyd-dznM"
// TODO

private const val TMDB_API_URL = "https://api.themoviedb.org/"
private const val TMDB_API_URL_V3 = "${TMDB_API_URL}3/"
private const val TMDB_API_URL_V4 = "${TMDB_API_URL}4/"

private const val PARAM_API_KEY = "api_key"
private const val PARAM_SESSION_ID = "session_id"

class TmdbApi(
        apiKey: String,
        sessionId: String? = null
) {

    private val interceptor = QueryInterceptor( mutableMapOf( PARAM_API_KEY to apiKey ) ).apply {
        sessionId?.let { addQueryParams( PARAM_SESSION_ID to it ) }
    }

    private val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor( interceptor )

    private val gson get() = GsonBuilder()
            .create()

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl( TMDB_API_URL_V3 )
            .addCallAdapterFactory( RxJava2CallAdapterFactory.createAsync() )
            .addConverterFactory( GsonConverterFactory.create(gson) )

    private inline fun <reified S> getService(): S = retrofitBuilder
            .client( httpClientBuilder.build() )
            .build()
            .create( S::class.java )

    val auth    by lazy { TmdbAuth( getService(), { setSession( it ) } ) }
    val account by lazy { getService<TmdbAccount>() }
    val config  by lazy { getService<TmdbConfig>() }
    val movies  by lazy { getService<TmdbMovies>() }
    val search  by lazy { getService<TmdbSearch>() }


    private fun setSession( session: Session ) {
        if (session.success) {
            interceptor.addQueryParams(PARAM_SESSION_ID to session.id)
        } else {
            interceptor.removeQueryParams( PARAM_SESSION_ID )
        }
    }

}


private class QueryInterceptor(private val params: MutableMap<String, String>) : Interceptor {

    internal fun addQueryParams(vararg queryParams: Pair<String, String>) {
        params.putAll(queryParams)
    }

    internal fun removeQueryParams( vararg keys: String) {
        keys.forEach { params.remove(it) }
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.request().let { request ->

            val url = request.url().newBuilder().apply {
                params.forEach { addQueryParameter(it.key, it.value) }
            }.build()

            println( url.toString() )

            val newRequest = request.newBuilder()
                    .url(url)
                    .method(request.method(), request.body())
                    .build()

            chain.proceed(newRequest)
        }

    }

}