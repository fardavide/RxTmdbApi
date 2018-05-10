package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class AccountState(

    @SerializedName("id") override  val id: Int,
    @SerializedName("favorite")     val favorite: Boolean,
    @SerializedName("rated")        val rated: Rated,
    @SerializedName("watchlist")    val watchlist: Boolean

) : IdElement