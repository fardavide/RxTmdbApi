package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis


private const val CHANGE_LIST = "change_list"
data class ChangeList (

    @SerializedName(Fields.CHANGES) @ColumnInfo(name = "${CHANGE_LIST}_${Fields.CHANGES}")
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
    @SerializedName("value")            val value: String,
    @SerializedName("original_value")   val originalValue: String

) : StringIdElement {

    val time get() = _time.timeInMillis

}


data class Change (

    @SerializedName("id") override      val id: Int,
    @SerializedName("adult")            val adult: Boolean?

) : IdElement, Pageable