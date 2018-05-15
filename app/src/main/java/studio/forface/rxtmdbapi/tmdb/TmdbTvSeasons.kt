@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.utils.DateQuery

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

internal const val SEASON_PATH = "season"
internal const val SEASON_NUMBER = "season_number"
private const val SEASON_ID = "season_id"
interface TmdbTvSeasons {

    /**
     * Get the TV season details by id.
     * Supports append_to_response. Read more about this here:
     * https://developers.themoviedb.org/3/getting-started/append-to-response .
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param extras all the extra information we want to get for the queried [Season].
     * @see Extras
     * @return a [Single] of [Season].
     */
    @GET("$TV_PATH/{$TV_ID}/$SEASON_PATH/{$SEASON_NUMBER}")
    fun getDetails(
            @Path(TV_ID)                          tvShowId: Int,
            @Path(SEASON_NUMBER)                  seasonNumber: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("append_to_response")    extras: Extras? = null
    ) : Single<Season>

    /**
     * Get the changes for a TV season. By default only the last 24 hours are returned.
     * You can query up to 14 days in a single query by using the start_date and end_date
     * query parameters.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ChangeList].
     */
    @GET("$TV_PATH/$SEASON_PATH/{$SEASON_ID}/changes")
    fun getChanges(
            @Path(SEASON_ID)                      seasonId: Int,
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ChangeList>

    /**
     * Get the cast and crew for a Tv season.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Credits].
     */
    @GET("$TV_PATH/{$TV_ID}/$SEASON_PATH/{$SEASON_NUMBER}/credits")
    fun getCredits(
            @Path(TV_ID)                          tvShowId: Int,
            @Path(SEASON_NUMBER)                  seasonNumber: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Credits>

    /**
     * Get the external ids for a Tv show. We currently support the following external sources:
     * - Tvdb
     * - Freebase mid*
     * - Freebase id*
     * - Tvrage*
     * *Defunct or no longer available as a service.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return [Single] of [ExternalIds].
     */
    @GET("$TV_PATH/{$TV_ID}/$SEASON_PATH/{$SEASON_NUMBER}/external_ids")
    fun getExternalIds(
            @Path(TV_ID)                          tvShowId: Int,
            @Path(SEASON_NUMBER)                  seasonNumber: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ExternalIds>

    /**
     * Get the images that belong to a Tv season.
     * Querying images with a language parameter will filter the results. If you want to include a
     * fallback language (especially useful for backdrops) you can use the include_image_language
     * parameter. This should be a comma separated value like so: include_image_language=en,null.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeImageLanguages a list of SO 639-1 values.
     * @return a [Single] of [Images].
     */
    @GET("$TV_PATH/{$TV_ID}/$SEASON_PATH/{$SEASON_NUMBER}/images")
    fun getImages(
            @Path(TV_ID)                          tvShowId: Int,
            @Path(SEASON_NUMBER)                  seasonNumber: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_image_language")includeImageLanguages: String? = null
    ) : Single<Images>

    /**
     * Get the videos that have been added to a Tv season.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Videos].
     */
    @GET("$TV_PATH/{$TV_ID}/$SEASON_PATH/{$SEASON_NUMBER}/videos")
    fun getVideos(
            @Path(TV_ID)                          tvShowId: Int,
            @Path(SEASON_NUMBER)                  seasonNumber: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Videos>

}