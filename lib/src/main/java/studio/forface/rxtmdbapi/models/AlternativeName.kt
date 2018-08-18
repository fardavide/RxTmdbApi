package studio.forface.rxtmdbapi.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class AlternativeTitles (
    @SerializedName(Fields.TITLES)  @ColumnInfo(name = Fields.TITLES)
    var titles: List<AlternativeTitle> = listOf()
)

data class AlternativeTitle (
    @SerializedName("iso_3166_1")   val iso31661: String,
    @SerializedName("title")        val title: String,
    @SerializedName("type")         val type: String?
)


data class AlternativeNames (
    @SerializedName(Fields.RESULTS) @ColumnInfo(name = Fields.RESULTS)
    var names: List<AlternativeName> = listOf()
)

data class AlternativeName (
    @SerializedName("iso_3166_1")   val iso31661: String,
    @SerializedName("name")         val name: String,
    @SerializedName("type")         val type: String?
)