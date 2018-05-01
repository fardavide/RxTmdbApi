package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.tmdb.models.Extras
import studio.forface.rxtmdbapi.tmdb.models.Movie
import studio.forface.rxtmdbapi.tmdb.models.ResultPage


interface TmdbMovies {

    @GET("movie/{movie_id}")
    fun getDetails(
            @Path("movie_id")               id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("append_to_response")    extras: Extras? = null
    ) : Single<Movie>

    @GET("movie/upcoming")
    fun getUpcoming(
            @Query("page")                  page: Int? = 1,
            @Query("language")              language: String? = TmdbApiConfig.language,
            @Query("region")                regionIso: String? = null
    ) : Single<ResultPage<Movie>>

}