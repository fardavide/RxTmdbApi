package studio.forface.rxtmdbapi.tmdb

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.Language

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "list"
private const val LIST_ID = "list_id"
interface TmdbLists {

    @GET("$BASE_PATH{$LIST_ID}")
    fun getDetails(
            @Path(LIST_ID)              id: String,
            @Query("language")  language: Language = TmdbApiConfig.language
    )

}