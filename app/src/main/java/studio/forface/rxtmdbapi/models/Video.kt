package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Videos (

    @SerializedName(Fields.RESULTS) @ColumnInfo(name = Fields.RESULTS)
    var results: List<Video> = listOf()

)


data class Video(

    @SerializedName("id")               val id: String,
    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("iso_3166_1")       val iso31661: String,
    @SerializedName("key")              val key: String,
    @SerializedName("name") override    val name: String,
    @SerializedName("site")             val site: String,
    @SerializedName("size")             val size: Int,
    @SerializedName("type")             val type: String

) : NamedElement