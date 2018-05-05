package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.tmdb.models.Movie
import studio.forface.rxtmdbapi.tmdb.models.Person
import studio.forface.rxtmdbapi.tmdb.models.ResultPage
import studio.forface.rxtmdbapi.tmdb.models.TvShow

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "search"
interface TmdbSearch {

    @GET("$BASE_PATH/movie?")
    fun searchMovie(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("year")                  year: Int? = null
    ): Single<ResultPage<Movie>>

    @GET("$BASE_PATH/person?")
    fun searchPeople(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults
    ): Single<ResultPage<Person>>

    @GET("$BASE_PATH/tv")
    fun searchTv(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("first_air_date_year")   startYear: Int? = null
    ): Single<ResultPage<TvShow>>

}