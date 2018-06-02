@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.utils.FavoriteRequest
import studio.forface.rxtmdbapi.utils.Sorting
import studio.forface.rxtmdbapi.utils.WatchlistRequest

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "/4/account"
private const val ACCOUNT_ID = "account_id"
private const val DEF_ACCOUNT = "0"
interface TmdbAccountV4 {

    /**
     * Get your account details.
     * @return a [Single] of [Account].
     */
    @GET(BASE_PATH)
    fun getDetails() : Single<Account>

    /**
     * Get all of the lists created by an account. Will include private lists if you are the owner.
     * @param id the id of the account to query, self account id it will be used if empty.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [MovieList].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/lists")
    fun getCreatedLists(
            @Path(ACCOUNT_ID)                     id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<MovieList>>

    /**
     * Get the list of favorite movies of an account.
     * @param accountId the id of the account to query.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending using [Sorting.ListMovieSorting]
     * params.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/movie/favorites")
    fun getFavoriteMovies(
            @Query(ACCOUNT_ID)                      accountId: String,
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.ListMovieSorting? = null
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of favorite TV shows of an account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$DEF_ACCOUNT/favorite/tv")
    fun getFavoriteTvShows(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of rated movies of an account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$DEF_ACCOUNT/rated/movies")
    fun getRatedMovies(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of rated Tv shows of an account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$DEF_ACCOUNT/rated/tv")
    fun getRatedTvShows(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of rated Tv episodes of an account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvEpisode].
     */
    @GET("$BASE_PATH/$DEF_ACCOUNT/rated/tv/episodes")
    fun getRatedTvEpisodes(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvEpisode>>

    /**
     * Get the list of movies in watchlist of an account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/$DEF_ACCOUNT/watchlist/movies")
    fun getMoviesWatchlist(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of Tv shows in watchlist of an account.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/$DEF_ACCOUNT/watchlist/tv")
    fun getTvShowsWatchlist(
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    @POST("$BASE_PATH/$DEF_ACCOUNT/favorite")
    fun manageFavorite( @Body request: FavoriteRequest) : Single<ResponseBody>

    @POST("$BASE_PATH/$DEF_ACCOUNT/watchlist")
    fun manageWatchlist( @Body request: WatchlistRequest) : Single<ResponseBody>

}

fun TmdbAccountV4.addMovieToFavorite( id: Int ) =
        manageFavorite(FavoriteRequest("movie", id, true))
fun TmdbAccountV4.removeMovieFromFavorite(id: Int ) =
        manageFavorite(FavoriteRequest("movie", id, false))

fun TmdbAccountV4.addMovieToWatchlist( id: Int ) =
        manageWatchlist(WatchlistRequest("movie", id, true))
fun TmdbAccountV4.removeMovieFromWatchlist( id: Int ) =
        manageWatchlist(WatchlistRequest("movie", id, false))
