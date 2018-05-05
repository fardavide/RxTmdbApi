@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.tmdb.models.*
import studio.forface.rxtmdbapi.utils.Sorting

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 *
 * NOTICE: Many of those methods can also be used within a Guest Session.
 */

private const val BASE_PATH = "account"
private const val ACCOUNT_ID = "account_id"
interface TmdbAccount {

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
            @Path(ACCOUNT_ID)                    id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<MovieList>>

    /**
     * Get the list of favorite movies of an account.
     * @param id the id of the account to query, self account id it will be used if empty.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/favorite/movies")
    fun getFavoriteMovies(
            @Path(ACCOUNT_ID)                    id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of favorite TV shows of an account.
     * @param id the id of the account to query, self account id it will be used if empty.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/favorite/tv")
    fun getFavoriteTvShows(
            @Path(ACCOUNT_ID)                    id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of rated movies of an account.
     * @param id the id of the account to query, self account id it will be used if empty.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/rated/movies")
    fun getRatedMovies(
            @Path(ACCOUNT_ID)                    id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<Movie>>

    /**
     * Get the list of rated Tv shows of an account.
     * @param id the id of the account to query, self account id it will be used if empty.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/rated/tv")
    fun getRatedTvShows(
            @Path(ACCOUNT_ID)                    id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvShow>>

    /**
     * Get the list of rated Tv episodes of an account.
     * @param id the id of the account to query, self account id it will be used if empty.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param sortBy sort the result ascending or descending by creationDate.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvEpisode].
     */
    @GET("$BASE_PATH/{$ACCOUNT_ID}/rated/tv/episodes")
    fun getRatedTvEpisodes(
            @Path(ACCOUNT_ID)                    id: Int? = 0,
            @Query("page")                  page: Int? = 1,
            @Query("sort_by")               sortBy: Sorting.CreationDate? = null,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<ResultPage<TvEpisode>>

}