@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.*

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_URL = "configuration"
interface TmdbConfig {

    /**
     * Get the system wide configuration information. Some elements of the API require some
     * knowledge of this configuration data. The purpose of this is to try and keep the actual API
     * responses as light as possible. It is recommended you cache this data within your
     * application and check for updates every few days.
     *
     * This method currently holds the data relevant to building image URLs as well as the change
     * key map.
     *
     * To build an image URL, you will need 3 pieces of data. The base_url, size and file_path.
     * Simply combine them all and you will have a fully qualified URL. Here’s an example URL:
     * https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
     *
     * You can also use built-in tools like:
     * @see ImagesConfig.bestSizeUrl or
     * @see mapToSizedUrls or
     * @see Image.getSizedUrl
     * using [ImageType].
     *
     * The configuration method also contains the list of change keys which can be useful if you
     * are building an app that consumes data from the change feed.
     * @return a [Single] of [ApiConfig].
     */
    @GET(BASE_URL)
    fun getApiConfig() : Single<ApiConfig>

    /**
     * Get the list of countries (ISO 3166-1 tags) used throughout TMDb.
     * @return a [Single] of [List] of [Country]
     */
    @GET("$BASE_URL/countries")
    fun getCountries() : Single<List<Country>>

    /**
     * Get the list of official genres for movies.
     * In the official doc this method belongs to Genres endpoint, but here is more appropriate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Genres]
     */
    @GET("genre/movie/list")
    fun getMovieGenres(
            @Query("language")      language: Language? = TmdbApiConfig.language
    ) : Single<Genres>

    /**
     * Get the list of official genres for Tv shows.
     * In the official doc this method belongs to Genres endpoint, but here is more appropriate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Genres]
     */
    @GET("genre/tv/list")
    fun getTvShowGenres(
            @Query("language")      language: Language? = TmdbApiConfig.language
    ) : Single<Genres>

    /**
     * Get the list of languages (ISO 639-1 tags) used throughout TMDb.
     * @return a [Single] of [List] of [Language].
     */
    @GET("$BASE_URL/languages")
    fun getLanguages() : Single<List<Language>>

}

/**
 * @see TmdbConfig.getApiConfig and then map taking [ApiConfig.imagesConfig].
 */
fun TmdbConfig.getImagesConfig(): Single<ImagesConfig> = getApiConfig().map { it.imagesConfig }