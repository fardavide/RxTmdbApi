@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import studio.forface.rxtmdbapi.models.AlternativeNames
import studio.forface.rxtmdbapi.models.Company
import studio.forface.rxtmdbapi.models.Images


/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_PATH = "company"
private const val COMPANY_ID = "company_id"
interface TmdbCompanies {

    /**
     * Get company details by id.
     * @return a [Single] of [Company].
     */
    @GET("$BASE_PATH/{$COMPANY_ID}")
    fun getDetails(
            @Path(COMPANY_ID) id: Int
    ): Single<Company>

    /**
     * Get the alternative names of a company.
     * @return a [Single] of [AlternativeNames].
     */
    @GET("$BASE_PATH/{$COMPANY_ID}/alternative_names")
    fun getAlternativeNames(
            @Path(COMPANY_ID) id: Int
    ): Single<AlternativeNames>

    /**
     * Get a companies logos by id.
     * There are two image formats that are supported for companies, PNG's and SVG's. You can see
     * which type the original file is by looking at the file_type field. We prefer SVG's as they
     * are resolution independent and as such, the width and height are only there to reflect the
     * original asset that was uploaded. An SVG can be scaled properly beyond those dimensions if
     * you call them as a PNG.
     *
     * For more information about how SVG's and PNG's can be used, take a read through here:
     * https://developers.themoviedb.org/3/getting-started/images .
     * @return a [Single] of [Images].
     */
    @GET("$BASE_PATH/{$COMPANY_ID}/images")
    fun getImages(
            @Path(COMPANY_ID) id: Int
    ): Single<Images>

}