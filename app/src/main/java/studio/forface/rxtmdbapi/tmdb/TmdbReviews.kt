package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import studio.forface.rxtmdbapi.models.Review


/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "review"
private const val REVIEWS_ID = "review_id"
interface TmdbReviews {

    /**
     * Get reviews details.
     * @return a [Single] of [Review]
     */
    @GET("$BASE_PATH/{$REVIEWS_ID}")
    fun getDetails(
            @Path(REVIEWS_ID)       id: String
    ): Single<Review>

}