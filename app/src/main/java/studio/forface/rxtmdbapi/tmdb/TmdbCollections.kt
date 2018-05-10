@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.Images
import studio.forface.rxtmdbapi.models.MovieCollection
import studio.forface.rxtmdbapi.models.Translations

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "collection"
private const val COLLECTION_ID = "collection_id"
interface TmdbCollections {

    /**
     * Get collection details by id.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [MovieCollection].
     */
    @GET("$BASE_PATH/{$COLLECTION_ID}")
    fun getDetails(
            @Path(COLLECTION_ID)                 id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<MovieCollection>

    /**
     * Get collection images by id.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Images].
     */
    @GET("$BASE_PATH/{$COLLECTION_ID}/images")
    fun getImages(
            @Path(COLLECTION_ID)                 id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Images>

    /**
     * Get collection translations by id.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @return a [Single] of [Translations].
     */
    @GET("$BASE_PATH/{$COLLECTION_ID}/translations")
    fun getTranslations(
            @Path(COLLECTION_ID)                 id: Int,
            @Query("language")              language: String? = TmdbApiConfig.language
    ) : Single<Translations<MovieCollection>>

}