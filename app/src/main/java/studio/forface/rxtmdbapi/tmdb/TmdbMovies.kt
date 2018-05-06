@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.tmdb.models.*
import studio.forface.rxtmdbapi.utils.DateQuery

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "movie"
private const val MOVIE_ID = "movie_id"
interface TmdbMovies {

    /**
     * Get the primary information about a movie.
     * Supports append_to_response. Read more about this: https://developers.themoviedb.org/3/getting-started/append-to-response .
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param extras all the extra information we want to get for the queried [Movie].
     * @see Extras
     * @return a [Single] of [Movie].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}")
    fun getDetails(
            @Path(MOVIE_ID)                      id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("append_to_response")    extras: Extras? = null
    ) : Single<Movie>

    /**
     * Get all of the alternative titles for a movie.
     * @param country
     * @return a [Single] of [AlternativeTitles].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/alternative_titles")
    fun getAlternativeTitles(
            @Path(MOVIE_ID)                      id: Int,
            @Query("country")               country: String? = null
    ) : Single<AlternativeTitles>

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * You can query up to 14 days in a single query by using the start_date and end_date
     * query parameters.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ChangeList].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/changes")
    fun getChanges(
            @Path(MOVIE_ID)                      id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ChangeList>

    /**
     * Get the cast and crew for a movie.
     * @return a [Single] of [Credits].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/credits")
    fun getCredits(
            @Path(MOVIE_ID)                      id: Int
    ) : Single<Credits>

    /**
     * Get the external ids for a movie. We currently support the following external sources:
     * - IMDb
     * - Facebook
     * - Instagram
     * - Twitter
     * @return [Single] of [ExternalIds].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/external_ids")
    fun getExternalIds(
            @Path(MOVIE_ID)                      id: Int
    ) : Single<ExternalIds>

    /**
     * Get the images that belong to a movie.
     * Querying images with a language parameter will filter the results. If you want to include a
     * fallback language (especially useful for backdrops) you can use the include_image_language
     * parameter. This should be a comma separated value like so: include_image_language=en,null.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeImageLanguages a list of SO 639-1 values.
     * @return a [Single] of [Images].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/images")
    fun getImages(
            @Path(MOVIE_ID)                      id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_image_language")includeImageLanguages: String? = null
    ) : Single<Images>

    /**
     * Get the keywords that have been added to a movie.
     * @return a [Single] of [Keywords].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/keywords")
    fun getKeywords(
            @Path(MOVIE_ID)                      id: Int
    ) : Single<Keywords>

    /**
     * Get the release date along with the certification for a movie.
     * @return a [Single] of [ReleaseDates]
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/release_dates")
    fun getReleaseDates(
            @Path(MOVIE_ID)                      id: Int
    ) : Single<ReleaseDates>

    /**
     * Get the videos that have been added to a movie.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Videos].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/videos")
    fun getVideos(
            @Path(MOVIE_ID)                      id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Videos>

    /**
     * Get a list of translations that have been created for a movie.
     * @return a [Single] of [Translations].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/translations")
    fun getTranslations(
            @Path(MOVIE_ID)                      id: Int
    ) : Single<Translations>

    /**
     * Get a list of recommended movies for a movie.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/recommendations")
    fun getRecommendations(
            @Path(MOVIE_ID)                      id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get a list of similar movies. This is not the same as the "Recommendation" system you see on
     * the website.
     * These items are assembled by looking at keywords and genres.     * @param page specify which
     * page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/similar")
    fun getSimilar(
            @Path(MOVIE_ID)                      id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get the user reviews for a movie.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Review].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/reviews")
    fun getReviews(
            @Path(MOVIE_ID)                      id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Movie>

    /**
     * Get a list of lists that this movie belongs to.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [MovieList].
     */
    @GET("$BASE_PATH/{$MOVIE_ID}/lists")
    fun getLists(
            @Path(MOVIE_ID)                      id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<MovieList>>

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