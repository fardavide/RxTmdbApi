@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import studio.forface.rxtmdbapi.models.*


/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "credit"
private const val CREDIT_ID = "credit_id"
interface TmdbCredits {

    /**
     * Get a movie or TV credit details by id.
     * @return a [Single] of [Credits].
     */
    @GET("$BASE_PATH/{$CREDIT_ID}")
    fun getDetails(
            @Path(CREDIT_ID) id: String
    ): Single<Credits>

}