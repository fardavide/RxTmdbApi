@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import studio.forface.rxtmdbapi.tmdb.models.CertificationsResult


private const val BASE_PATH = "certification"
interface TmdbCertifications {

    /**
     * Get an up to date list of the officially supported movie certifications on TMDb.
     * @return a [Single] of [CertificationsResult]
     */
    @GET("$BASE_PATH/movie/list")
    fun getMovieCertifications() : Single<CertificationsResult>

    /**
     * Get an up to date list of the officially supported Tv show certifications on TMDb.
     * @return a [Single] of [CertificationsResult]
     */
    @GET("$BASE_PATH/tv/list")
    fun getTvCertifications() : Single<CertificationsResult>

}