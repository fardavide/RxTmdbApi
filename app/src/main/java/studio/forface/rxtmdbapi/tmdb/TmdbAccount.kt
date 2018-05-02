package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import studio.forface.rxtmdbapi.tmdb.models.Account

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "account"
interface TmdbAccount {

    /**
     * Get your account details.
     * @return a [Single] of [Account].
     */
    @GET(BASE_PATH)
    fun getDetails() : Single<Account>

}