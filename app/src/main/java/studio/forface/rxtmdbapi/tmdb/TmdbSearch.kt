package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import studio.forface.rxtmdbapi.tmdb.models.Movie
import studio.forface.rxtmdbapi.tmdb.models.Person
import studio.forface.rxtmdbapi.tmdb.models.ResultPage
import studio.forface.rxtmdbapi.tmdb.models.Tv


interface TmdbSearch {

    @GET("search/movie?")
    fun searchMovie(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults,
            @Query("year")                  year: Int? = null
    ): Single<ResultPage<Movie>>

    @GET("search/person?")
    fun searchPeople(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults
    ): Single<ResultPage<Person>>

    @GET("search/tv")
    fun searchTv(
            @Query("query")                 query: String,
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("first_air_date_year")   startYear: Int? = null
    ): Single<ResultPage<Tv>>

}