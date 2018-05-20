package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class AlternativeTitles (

    @SerializedName(Fields.TITLES)  @ColumnInfo(name = Fields.TITLES)
    var titles: List<AlternativeName> = listOf()

)


data class AlternativeNames (

    @SerializedName(Fields.RESULTS) @ColumnInfo(name = Fields.RESULTS)
    var names: List<AlternativeName> = listOf()

)


data class AlternativeName (

    @SerializedName("iso_3166_1")   val iso31661: String,
    @SerializedName("title")        val title: String,
    @SerializedName("type")         val type: String?

)