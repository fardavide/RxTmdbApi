package studio.forface.rxtmdbapi.models.requests

import com.google.gson.annotations.SerializedName

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
data class WatchlistRequest (
    @SerializedName("media_type")   val mediaType: String,
    @SerializedName("media_id")     val mediaId: Int,
    @SerializedName("watchlist")    val watchlist: Boolean
)