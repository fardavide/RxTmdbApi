package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis


data class ChangeList (

    @SerializedName("changes")          val changes: List<Changes>

)


data class Changes(

    @SerializedName("key")              val key: String,
    @SerializedName("items")            val items: List<Item>

)


data class Item (

        @SerializedName("id")               val id: String,
        @SerializedName("action")           val action: String,
        @SerializedName("time") private     val _time: String,
        @SerializedName("iso_639_1")        val iso6391: String,
        @SerializedName("value")            val value: String,
        @SerializedName("original_value")   val originalValue: String

) {

    val time get() = _time.timeInMillis

}