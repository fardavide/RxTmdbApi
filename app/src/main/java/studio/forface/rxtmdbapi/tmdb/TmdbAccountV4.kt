@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.utils.Sorting

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
private const val BASE_PATH = "/4/account"
private const val ACCOUNT_ID = "account_id"
private const val DEF_ACCOUNT = "0"
internal const val PATH_ACCOUNT_ID = "\$$ACCOUNT_ID"
interface TmdbAccountV4 {

    /**
     * Get your account details.
     * @return a [Single] of [Account].
     */
    @GET(BASE_PATH)
    fun getDetails() : Single<Account>

    /**
     * Get all of the lists created by the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @return a [Single] of [ResultPage] of [MovieList].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/lists")
    fun getLists(
            @Query("page")                  page: Int? = 1
    ) : Single<ResultPage<MovieList>>

    /**
     * Get the list of favorite movies of the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListMovieSorting]
     * params.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/movie/favorites")
    fun getFavoriteMovies(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListMovieSorting? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of favorite TV shows of the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListTvShowSorting].
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/tv/favorites")
    fun getFavoriteTvShows(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListTvShowSorting? = null
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of movies recommendations for the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListMovieSorting]
     * params.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/movie/recommendations")
    fun getMovieRecommendations(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListMovieSorting? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of TV shows recommendations for the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListTvShowSorting].
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/tv/recommendations")
    fun getTvShowsRecommendations(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListTvShowSorting? = null
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of rated movies for the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListMovieSorting]
     * params.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/movie/rated")
    fun getRatedMovies(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListMovieSorting? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of rated TV shows for the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListTvShowSorting].
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/tv/rated")
    fun getRatedTvShows(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListTvShowSorting? = null
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of movies in watchlist for the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListMovieSorting]
     * params.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/movie/watchlist")
    fun getMoviesWatchlist(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListMovieSorting? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of TV shows in watchlist for the logged account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListTvShowSorting].
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$PATH_ACCOUNT_ID/tv/watchlist")
    fun getTvShowsWatchlist(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListTvShowSorting? = null
    ) : Single<ResultPage<TvShow>>

}