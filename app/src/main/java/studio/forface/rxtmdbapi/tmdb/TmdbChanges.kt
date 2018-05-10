@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.Change
import studio.forface.rxtmdbapi.models.ResultPage
import studio.forface.rxtmdbapi.utils.DateQuery


private const val CHANGES_PATH = "changes"
interface TmdbChanges {

    /**
     * Get a list of all of the Movie ids that have been changed in the past 24 hours.
     * You can query it for up to 14 days worth of changed IDs at a time with the start_date and
     * end_date query parameters. 100 items are returned per page.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ResultPage] of [Change].
     */
    @GET("movie/$CHANGES_PATH")
    fun getMovieChanges(
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ResultPage<Change>>

    /**
     * Get a list of all of the Tv show ids that have been changed in the past 24 hours.
     * You can query it for up to 14 days worth of changed IDs at a time with the start_date and
     * end_date query parameters. 100 items are returned per page.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ResultPage] of [Change].
     */
    @GET("tv/$CHANGES_PATH")
    fun getTvShowChanges(
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ResultPage<Change>>

    /**
     * Get a list of all of the People ids that have been changed in the past 24 hours.
     * You can query it for up to 14 days worth of changed IDs at a time with the start_date and
     * end_date query parameters. 100 items are returned per page.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param startDate use [DateQuery] for build a formatted String.
     * @param endDate use [DateQuery] for build a formatted String.
     * @return a [Single] of [ResultPage] of [Change].
     */
    @GET("person/$CHANGES_PATH")
    fun getPersonChanges(
            @Query("page")                  page: Int? = 1,
            @Query("start_date")            startDate: DateQuery? = null,
            @Query("end_date")              endDate: DateQuery? = null
    ) : Single<ResultPage<Change>>

}