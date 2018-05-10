package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


private const val ALTERNATIVE_TITLES = Fields.ALTERNATIVE_TITLES
data class AlternativeTitles (

    @SerializedName(Fields.TITLES)  @ColumnInfo(name = "${ALTERNATIVE_TITLES}_${Fields.TITLES}")
    var titles: List<AlternativeTitle> = listOf()

)


data class AlternativeTitle (

    @SerializedName("iso_3166_1")   val iso31661: String,
    @SerializedName("title")        val title: String,
    @SerializedName("type")         val type: String?

)