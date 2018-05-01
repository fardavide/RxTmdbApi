package studio.forface.rxtmdbapi.tmdb

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


private const val TMDB_API_URL = "https://api.themoviedb.org/"

private const val TMDB_API_URL_V3 = "${TMDB_API_URL}3/"
private const val TMDB_API_URL_V4 = "${TMDB_API_URL}4/"

private const val TMDB_API_KEY = "6328c07c1c982565d446d22aaa27a945"
private const val TMDB_READ_ACCESS_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MzI4YzA3YzFjOTgyNTY1ZDQ0NmQyMmFhYTI3YTk0NSIsInN1YiI6IjU5MzFjZGIzYzNhMzY4NGYwMTAwMjRkOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.c3eoSAnqXxHv8604RVjPvcekdLN0IDPCzxMVyd-dznM"


class TmdbApi {

    private val interceptor = QueryInterceptor( mutableMapOf( "api_key" to TMDB_API_KEY ) )

    private val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor( interceptor )

    private val gson get() = GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl( TMDB_API_URL_V3 )
            .addCallAdapterFactory( RxJava2CallAdapterFactory.createAsync() )
            .addConverterFactory( GsonConverterFactory.create(gson) )


    private inline fun <reified S> getService(): S = retrofitBuilder
            .client( httpClientBuilder.build() )
            .build()
            .create( S::class.java )


    val auth    get() = getService<TmdbAuth>()
    val config  get() = getService<TmdbConfig>()
    val movies  get() = getService<TmdbMovies>()
    val search  get() = getService<TmdbSearch>()

}


private class QueryInterceptor(val params: MutableMap<String, String>) : Interceptor {

    internal fun addQueryParams(queryParams: Map<String, String>) {
        params.putAll(queryParams)
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