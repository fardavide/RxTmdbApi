package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import studio.forface.rxtmdbapi.tmdb.models.Language

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

interface TmdbConfig {

    @GET("configuration/languages")
    fun getLanguages() : Single<List<Language>>

}