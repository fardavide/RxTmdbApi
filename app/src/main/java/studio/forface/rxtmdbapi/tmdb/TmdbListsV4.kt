@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.Language
import studio.forface.rxtmdbapi.models.ListPage
import studio.forface.rxtmdbapi.models.requests.ListV4CreateRequest
import studio.forface.rxtmdbapi.models.requests.ListV4UpdateRequest
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.Sorting


private const val BASE_PATH = "/4/list"
private const val LIST_ID = "list_id"
/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
interface TmdbListsV4 {

    /**
     * Get details of a List.
     * Private lists can only be accessed by their owners and therefore require a valid user access
     * token.
     * @param page specify which page to query. Minimum 1, maximum 1000.
     * @param language a ISO 639-1 value to display translated data for the fields that support it.
     * @param sortBy sort the result ascending or descending using [Sorting.ListSorting].
     * @return a [Single] of [ListPage].
     */
    @GET("$BASE_PATH/{$LIST_ID}")
    fun getDetails(
            @Path(LIST_ID)              id: Int,
            @Query("page")      page: Int? = 1,
            @Query("language")  language: Language? = TmdbApiConfig.language,
            @Query("sort_by")   sortBy: Sorting.ListSorting? = null
    ) : Single<ListPage>

    /**
     * Create a new list.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @POST(BASE_PATH)
    @FormUrlEncoded
    fun createList(
            @Field("name")         name: String,
            @Field("description")  description: String? = null,
            @Field("iso_639_1")    language: Language = TmdbApiConfig.language,
            @Field("public")       public: Boolean? = null,
            @Field("iso_3166_1")   region: String? = null
    ) : Single<ResponseBody>

    /**
     * Update the details of a list.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @PUT("$BASE_PATH/{$LIST_ID}")
    fun updateList(
            @Path(LIST_ID) id: Int,
            @Body list: ListV4UpdateRequest
    ) : Single<ResponseBody>

    /**
     * Clear all of the items from a list in a single request. This action cannot be reversed so
     * use it with caution.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @GET("$BASE_PATH/{$LIST_ID}/clear")
    fun clearList(
            @Path(LIST_ID) id: Int
    ) : Single<ResponseBody>

    /**
     * Delete a list by id. This action is not reversible so take care when issuing it.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @DELETE("$BASE_PATH/{$LIST_ID}")
    fun deleteList(
            @Path(LIST_ID) id: Int
    ) : Single<ResponseBody>

}