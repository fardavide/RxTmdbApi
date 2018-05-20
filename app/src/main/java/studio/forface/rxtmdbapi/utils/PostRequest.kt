package studio.forface.rxtmdbapi.utils

import com.google.gson.annotations.SerializedName


interface Request {
    val mediaType: String
    val mediaId: Int
}


data class FavoriteRequest (

    @SerializedName("media_type") override  val mediaType: String,
    @SerializedName("media_id") override    val mediaId: Int,
    @SerializedName("favorite")             val watchlist: Boolean

) : Request


data class WatchlistRequest (

    @SerializedName("media_type") override  val mediaType: String,
    @SerializedName("media_id") override    val mediaId: Int,
    @SerializedName("watchlist")            val watchlist: Boolean

) : Request

