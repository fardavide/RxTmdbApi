@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.tmdb.models.Extras
import studio.forface.rxtmdbapi.tmdb.models.Movie
import studio.forface.rxtmdbapi.tmdb.models.ResultPage
import studio.forface.rxtmdbapi.tmdb.models.Review

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "movie"
interface TmdbMovies {

    /**
     * Get the primary information about a movie.
     * Supports append_to_response. Read more about this: https://developers.themoviedb.org/3/getting-started/append-to-response .
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param extras all the extra information we want to get for the queried [Movie].
     * @see Extras
     * @return a [Single] of [Movie].
     */
    @GET("$BASE_PATH/{movie_id}")
    fun getDetails(
            @Path("movie_id")               id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("append_to_response")    extras: Extras? = null
    ) : Single<Movie>

    /**
     * Get the user reviews for a movie.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Review].
     */
    @GET("$BASE_PATH/{movie_id}/reviews")
    fun getReviews(
            @Path("movie_id")               id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Movie>

    /**
     * Get the most newly created movie. This is a live response and will continuously change.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Movie].
     */
    @GET("$BASE_PATH/latest")
    fun getLatest(
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Movie>

    /**
     * Get a list of movies in theatres. This is a release type query that looks for all movies that
     * have a release type of 2 or 3 within the specified date range.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param region a ISO 3166-1 parameter which will narrow the search to only look for theatrical release
     * dates within the specified country.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/now_playing")
    fun getNowPlaying(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("region")                region: String? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get a list of the current popular movies on TMDb. This list updates daily.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param region an ISO 3166-1 code to filter release dates. Must be uppercase.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/popular")
    fun getPopular(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("region")                region: String? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get the top rated movies on TMDb.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param region an ISO 3166-1 code to filter release dates. Must be uppercase.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/top_rated")
    fun getTopRated(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("region")                region: String? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get a list of upcoming movies in theatres. This is a release type query that looks for all
     * movies that have a release type of 2 or 3 within the specified date range.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param region a ISO 3166-1 parameter which will narrow the search to only look for theatrical release
     * dates within the specified country.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/upcoming")
    fun getUpcoming(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("region")                region: String? = null
    ) : Single<ResultPage<Movie>>

}