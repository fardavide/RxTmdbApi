@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.utils.DateQuery

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "tv"
private const val TV_ID = "tv_id"
interface TmdbTv {

    /**
     * Get the primary information about a Tv show.
     * Supports append_to_response. Read more about this: https://developers.themoviedb.org/3/getting-started/append-to-response .
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param extras all the extra information we want to get for the queried [TvShow].
     * @see Extras
     * @return a [Single] of [TvShow].
     */
    @GET("$BASE_PATH/{$TV_ID}")
    fun getDetails(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("append_to_response")    extras: Extras? = null
    ) : Single<TvShow>

    /**
     * Get all of the alternative titles for a Tv show.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [AlternativeTitles].
     */
    @GET("$BASE_PATH/{$TV_ID}/alternative_titles")
    fun getAlternativeTitles(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<AlternativeTitles>

    /**
     * Get the changes for a Tv show. By default only the last 24 hours are returned.
     * You can query up to 14 days in a single query by using the start_date and end_date
     * query parameters.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ChangeList].
     */
    @GET("$BASE_PATH/{$TV_ID}/changes")
    fun getChanges(
            @Path(TV_ID)                          id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ChangeList>

    /**
     * Get the list of content ratings (certifications) that have been added to a TV show.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ContentRatings].
     */
    @GET("$BASE_PATH/{$TV_ID}/content_ratings")
    fun getContentRatings(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ContentRatings>

    /**
     * Get the cast and crew for a Tv show.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Credits].
     */
    @GET("$BASE_PATH/{$TV_ID}/credits")
    fun getCredits(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Credits>

    /**
     * Get the external ids for a Tv show. We currently support the following external sources:
     * - IMDb
     * - Facebook
     * - Instagram
     * - Twitter
     * - Tvdb
     * - Freebase mid
     * - Freebase id
     * - Tvrage
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return [Single] of [ExternalIds].
     */
    @GET("$BASE_PATH/{$TV_ID}/external_ids")
    fun getExternalIds(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ExternalIds>

    /**
     * Get the images that belong to a Tv show.
     * Querying images with a language parameter will filter the results. If you want to include a
     * fallback language (especially useful for backdrops) you can use the include_image_language
     * parameter. This should be a comma separated value like so: include_image_language=en,null.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeImageLanguages a list of SO 639-1 values.
     * @return a [Single] of [Images].
     */
    @GET("$BASE_PATH/{$TV_ID}/images")
    fun getImages(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_image_language")includeImageLanguages: String? = null
    ) : Single<Images>

    /**
     * Get the keywords that have been added to a Tv show.
     * @return a [Single] of [Keywords].
     */
    @GET("$BASE_PATH/{$TV_ID}/keywords")
    fun getKeywords(
            @Path(TV_ID)                      id: Int
    ) : Single<Keywords>

    /**
     * Get the videos that have been added to a Tv show.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Videos].
     */
    @GET("$BASE_PATH/{$TV_ID}/videos")
    fun getVideos(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Videos>

    /**
     * Get a list of translations that have been created for a Tv show.
     * @return a [Single] of [Translations] of [TvShow].
     */
    @GET("$BASE_PATH/{$TV_ID}/translations")
    fun getTranslations(
            @Path(TV_ID)                          id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Translations<TvShow>>

    /**
     * Get a list of recommended Tv shows for a Tv show.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/{$TV_ID}/recommendations")
    fun getRecommendations(
            @Path(TV_ID)                         id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get a list of similar TV shows. These items are assembled by looking at keywords and genres.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/{$TV_ID}/similar")
    fun getSimilar(
            @Path(TV_ID)                         id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the user reviews for a Tv show.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Review].
     */
    @GET("$BASE_PATH/{$TV_ID}/reviews")
    fun getReviews(
            @Path(TV_ID)                        id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Review>>

    /**
     * Get a list of seasons or episodes that have been screened in a film festival or theatre.
     * @return a [Single] of [TvEpisodeReferences].
     */
    @GET("$BASE_PATH/{$TV_ID}/screened_theatrically")
    fun getScreenedTheatrically(
            @Path(TV_ID)                        id: Int
    ) : Single<TvEpisodeReferences>

    /**
     * Get the most newly created Tv shows. This is a live response and will continuously change.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [TvShow].
     */
    @GET("$BASE_PATH/latest")
    fun getLatest(
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<TvShow>

    /**
     * Get a list of the current popular Tv shows on TMDb. This list updates daily.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/popular")
    fun getPopular(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the top rated Tv shows on TMDb.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/top_rated")
    fun getTopRated(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get a list of TV shows that are airing today. This query is purely day based as we do not
     * currently support airing times.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/airing_today")
    fun getAiringToday(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get a list of shows that are currently on the air.
     * This query looks for any TV show that has an episode with an air date in the next 7 days.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/on_the_air")
    fun getOnTheAir(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Rate a Tv show.
     * A valid session or guest session ID is required. You can read more about how this works here:
     * https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id .
     * @param value the value of the rating you want to submit.
     * The value is expected to be between 0.5 and 10.0 and the decimal should be 0 or 5.
     * @return a [Single] of [ResponseBody].
     */
    @POST("$BASE_PATH/{$TV_ID}/rating")
    @FormUrlEncoded
    fun rateTvShow(
            @Path(TV_ID)                         id: Int,
            @Field("value")                 value: Number
    ) : Single<ResponseBody>

    /**
     * Remove your rating for a Tv show.
     * A valid session or guest session ID is required. You can read more about how this works here:
     * https://developers.themoviedb.org/3/authentication/how-do-i-generate-a-session-id .
     * @return a [Single] of [ResponseBody].
     */
    @DELETE("$BASE_PATH/{$TV_ID}/rating")
    fun removeTvShowRating(
            @Path(TV_ID)                         id: Int
    ) : Single<ResponseBody>

}