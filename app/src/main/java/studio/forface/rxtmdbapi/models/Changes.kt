package studio.forface.rxtmdbapi.models

import androidx.room.ColumnInfo
import androidx.room.RoomWarnings
import androidx.room.RoomWarnings.DEFAULT_CONSTRUCTOR
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis

@SuppressWarnings(DEFAULT_CONSTRUCTOR)
data class ChangeList (

    @SerializedName(Fields.CHANGES) @ColumnInfo(name = Fields.CHANGES)
    var changes: List<Changes> = listOf()

)


data class Changes(

    @SerializedName("key")              val key: String,
    @SerializedName("items")            val items: List<Item>

)


data class Item (

    @SerializedName("id") override      val id: String,
    @SerializedName("action")           val action: String,
    @SerializedName("time") private     val _time: String,
    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("value")            val value: Any?,
    @SerializedName("original_value")   val originalValue: String

) : StringIdElement {

    val time get() = _time.timeInMillis

}


data class Change (

    @SerializedName("id") override      val id: Int,
    @SerializedName("adult")            val adult: Boolean?

) : IdElement, Pageable