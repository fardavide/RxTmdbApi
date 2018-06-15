package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.*

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "search"
interface TmdbSearch {

    /**
     * Search multiple models in a single request. Multi search currently supports searching for
     * movies, tv shows and people in a single request.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeAdults choose whether to include adult (pornography) content in the results.
     * @return a [Single] of [ResultPage] of [Media].
     */
    @GET("$BASE_PATH/multi")
    fun multiSearch(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("region")                region: String? = null
    ): Single<ResultPage<Media>>

    /**
     * Search for companies.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @return a [Single] of [ResultPage] of [Company].
     */
    @GET("$BASE_PATH/company")
    fun searchCompanies(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1
    ): Single<ResultPage<Company>>

    /**
     * Search for collections.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [MovieCollection].
     */
    @GET("$BASE_PATH/collection")
    fun searchCollections(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language
    ): Single<ResultPage<MovieCollection>>

    /**
     * Search for languages.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @return a [Single] of [ResultPage] of [Language].
     */
    @GET("$BASE_PATH/keyword")
    fun searchLanguages(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1
    ): Single<ResultPage<Language>>

    /**
     * Search for Movies.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeAdults choose whether to include adult (pornography) content in the results.
     * @param region an ISO 3166-1 code to filter release dates. Must be uppercase.
     * @return a [Single] of [ResultPage] of [MovieCollection].
     */
    @GET("$BASE_PATH/movie")
    fun searchMovies(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("region")                region: String? = null,
            @Query("year")                  year: Int? = null,
            @Query("primary_release_year")  primaryReleaseYear: Int? = null
    ): Single<ResultPage<Movie>>

    /**
     * Search for People.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeAdults choose whether to include adult (pornography) content in the results.
     * @param region an ISO 3166-1 code to filter release dates. Must be uppercase.
     * @return a [Single] of [ResultPage] of [Person].
     */
    @GET("$BASE_PATH/person")
    fun searchPeople(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("region")                region: String? = null
    ): Single<ResultPage<Person>>

    /**
     * Search for Tv shows.
     * @param query Pass a text query to search. This value should be URI encoded.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [ResultPage] of [TvShow].
     */
    @GET("$BASE_PATH/tv")
    fun searchTvShows(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: Language? = TmdbApiConfig.language,
            @Query("first_air_date_year")   startYear: Int? = null
    ): Single<ResultPage<TvShow>>

}