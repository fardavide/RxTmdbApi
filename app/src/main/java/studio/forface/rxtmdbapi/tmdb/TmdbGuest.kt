@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.utils.Sorting

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

internal const val PATH_GUEST_SESSION_ID = "\$id"
private const val BASE_PATH = "guest_session"
interface TmdbGuest {

    /**
     * Get the list of rated movies of a guest session.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$PATH_GUEST_SESSION_ID/rated/movies")
    fun getRatedMovies(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of rated Tv shows of a guest session.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$PATH_GUEST_SESSION_ID/rated/tv")
    fun getRatedTvShows(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of rated Tv episodes of a guest session.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvEpisode].
     */
    @GET("$BASE_PATH/$PATH_GUEST_SESSION_ID/rated/tv/episodes")
    fun getRatedTvEpisodes(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ) : Single<ResultPage<TvEpisode>>

}