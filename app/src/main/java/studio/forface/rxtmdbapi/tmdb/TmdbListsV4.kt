@file:Suppress("unused")

package studio.forface.rxtmdbapi.tmdb

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.http.*
import studio.forface.rxtmdbapi.models.Language
import studio.forface.rxtmdbapi.models.ListPage
import studio.forface.rxtmdbapi.models.MediaType
import studio.forface.rxtmdbapi.models.requests.ItemRequest
import studio.forface.rxtmdbapi.models.requests.ItemsRequest
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
    @FormUrlEncoded
    fun updateList(
            @Path(LIST_ID)                 id: Int,
            @Field("name")         name: String,
            @Field("description")  description: String? = null,
            @Field("public")       public: Boolean? = null,
            @Field("sort_by")      sortBy: Sorting.ListMovieSorting? = null
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

    /**
     * Add items to a list. We support essentially an unlimited number of items to be posted at a
     * time. Both movie and TV series are support.
     * The results of this query will return a results array. Each result includes a success field.
     * If a result is false this will usually indicate that the item already exists on the list.
     * It may also indicate that the item could not be found.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @POST("$BASE_PATH/{$LIST_ID}/items")
    fun addItems(
            @Path(LIST_ID)  listId: Int,
            @Body           items: ItemsRequest
    ) : Single<ResponseBody>

    /**
     * Remove items from a list. You can remove multiple items at a time.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @HTTP(method = "DELETE", path = "$BASE_PATH/{$LIST_ID}/items", hasBody = true)
    fun removeItems(
            @Path(LIST_ID)  listId: Int,
            @Body           items: ItemsRequest
    ) : Single<ResponseBody>

    /**
     * Add/update comment to items into a list. You can comment multiple items at a time.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody].
     */
    @PUT("$BASE_PATH/{$LIST_ID}/items")
    fun commentItems(
            @Path(LIST_ID)  listId: Int,
            @Body           items: ItemsRequest
    ) : Single<ResponseBody>

    /**
     * Check if the item is already added to the list.
     * You will need to have valid user access token in order to do that.
     * @return a [Single] of [ResponseBody] if available, else error 404.
     */
    @Deprecated("It is required since is a base API method, " +
            "throw error 404 if item is not available, " +
            "Use hasItem() for have a Boolean instead",
            ReplaceWith("hasItem()"))
    @GET("$BASE_PATH/{$LIST_ID}/item_status")
    fun checkItemStatus(
            @Path(LIST_ID)                  listId: Int,
            @Query("media_type")    mediaType: MediaType,
            @Query("media_id")      mediaId: Int
    ) : Single<ResponseBody>
}

/**
 * Remove comment to items into a list. You can comment multiple items at a time.
 * You will need to have valid user access token in order to do that.
 * @return a [Single] of [ResponseBody].
 */
fun TmdbListsV4.removeComments( listId: Int, itemsRequest: ItemsRequest ) =
        commentItems( listId, itemsRequest.addEmptyComments() )

/**
 * Create a new [ItemsRequest] with same params but with [EMPTY_STRING] as [ItemRequest.comment],
 * instead of 'null'.
 * @return an [ItemsRequest].
 */
private fun ItemsRequest.addEmptyComments() = ItemsRequest (
        * items.map { ItemRequest( it.mediaType, it.id, EMPTY_STRING ) }.toTypedArray()
)

/**
 * @see TmdbListsV4.checkItemStatus , this is a wrapper for return 'false' instead of error 404.
 * @return a [Single] of [Boolean].
 */
@Suppress("DEPRECATION")
fun TmdbListsV4.hasItem( listId: Int, mediaType: MediaType, mediaId: Int ): Single<Boolean> =
        checkItemStatus( listId, mediaType, mediaId )
                .map { true }
                .onErrorResumeNext {
                    val errorCode = ( it as? HttpException )?.code()
                    if ( errorCode == 404 )Single.just(false )
                    else Single.error( it )
                }
