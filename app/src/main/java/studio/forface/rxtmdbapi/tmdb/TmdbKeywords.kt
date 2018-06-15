@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.Language
import studio.forface.rxtmdbapi.models.Movie
import studio.forface.rxtmdbapi.models.Network
import studio.forface.rxtmdbapi.models.ResultPage

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "keyword"
private const val KEYWORD_ID = "keyword_id"
interface TmdbKeywords {

    /**
     * Get keyword details.
     * @return a [Single] of [Network]
     */
    @GET("$BASE_PATH/{$KEYWORD_ID}")
    fun getDetails(
            @Path(KEYWORD_ID)                    id: String
    ): Single<Network>

    /**
     * Get the movies that belong to a keyword.
     * DEPRECATED in API, use [TmdbDiscover.movieDiscover] instead of this method as it is
     * much more flexible.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param includeAdults choose whether to include adult (pornography) content in the results.
     * @return a [Single] of [ResultPage] of [Movie].
     */
    @GET("$BASE_PATH/{$KEYWORD_ID}/movies")
    @Deprecated(
            "Deprecated in API",
            ReplaceWith("TmdbDiscover.movieDiscover" )
    )
    fun getMovies(
            @Path(KEYWORD_ID)                       id: String,
            @Query("language")              language: Language? = TmdbApiConfig.language,
            @Query("include_adult")         includeAdults: Boolean? = TmdbApiConfig.includeAdults
    ): Single<ResultPage<Movie>>

}