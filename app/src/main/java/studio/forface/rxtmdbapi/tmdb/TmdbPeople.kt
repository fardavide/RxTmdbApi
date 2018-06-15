@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.utils.DateQuery

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "person"
private const val PERSON_ID = "person_id"
interface TmdbPeople {

    /**
     * Get the primary information about a person.
     * Supports append_to_response. Read more about this: https://developers.themoviedb.org/3/getting-started/append-to-response .
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param extras all the extra information we want to get for the queried [Movie].
     * @see Extras
     * @return a [Single] of [Person].
     */
    @GET("$BASE_PATH/{$PERSON_ID}")
    fun getDetails(
            @Path(PERSON_ID)                     id: Int,
            @Query("language")              language: Language? = TmdbApiConfig.language,
            @Query("append_to_response")    extras: Extras? = null
    ) : Single<Person>

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * You can query up to 14 days in a single query by using the start_date and end_date
     * query parameters.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ChangeList].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/changes")
    fun getChanges(
            @Path(PERSON_ID)                     id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ChangeList>

    /**
     * Get the movie credits for a person.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Credits].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/movie_credits")
    fun getMovieCredits(
            @Path(PERSON_ID)                      id: Int,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<Credits>

    /**
     * Get the TV show credits for a person.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Credits].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/tv_credits")
    fun getTvCredits(
            @Path(PERSON_ID)                      id: Int,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<Credits>

    /**
     * Get the movie and TV credits together in a single response.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Credits].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/combined_credits")
    fun getCombinedCredits(
            @Path(PERSON_ID)                      id: Int,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<Credits>

    /**
     * Get the external ids for a person. We currently support the following external sources:
     * - IMDb
     * - Facebook
     * - Freebase MID
     * - Freebase ID
     * - Instagram
     * - TvRage
     * - Twitter
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return [Single] of [ExternalIds].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/external_ids")
    fun getExternalIds(
            @Path(PERSON_ID)                     id: Int,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<ExternalIds>

    /**
     * Get the images for a person.
     * @return a [Single] of [Images].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/images")
    fun getImages(
            @Path(PERSON_ID)                     id: Int
    ) : Single<Images>

    /**
     * Get the images that this person has been tagged in.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Image].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/tagged_images")
    fun getTaggedImages(
            @Path(PERSON_ID)                     id: Int,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language
     ) : Single<ResultPage<Image>>

    /**
     * Get a list of translations that have been created for a movie.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Translations] of [Person].
     */
    @GET("$BASE_PATH/{$PERSON_ID}/translations")
    fun getTranslations(
            @Path(PERSON_ID)                     id: Int,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<Translations<Person>>

    /**
     * Get the most newly created movie. This is a live response and will continuously change.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Person].
     */
    @GET("$BASE_PATH/latest")
    fun getLatest(
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<Person>

    /**
     * Get a list of the current popular movies on TMDb. This list updates daily.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Person].
     */
    @GET("$BASE_PATH/popular")
    fun getPopular(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<ResultPage<Person>>

}