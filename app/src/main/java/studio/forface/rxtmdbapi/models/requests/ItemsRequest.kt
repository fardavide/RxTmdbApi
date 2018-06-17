package studio.forface.rxtmdbapi.models.requests

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.MediaType

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
class ItemsRequest {

    constructor( vararg items: Pair<MediaType, Int> ) {
        this.items = items.map { ItemRequest( it.first, it.second ) }
    }

    constructor( vararg items: ItemRequest ) {
        this.items = items.toList()
    }

    @SerializedName("items") internal val items: List<ItemRequest>
}

data class ItemRequest(
    @SerializedName("media_type")   val mediaType: String,
    @SerializedName("media_id")     val id: Int,
    @SerializedName("comment")      val comment: String? = null
) {
    /**
     * Overloaded constructor that accepts [MediaType] as mediaType params:
     * it will take [MediaType.string] for call the primary constructor.
     */
    constructor( mediaType: MediaType, id: Int, comment: String? = null )
            : this( mediaType.string, id, comment )
}