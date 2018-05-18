package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Keywords(

    @SerializedName(Fields.KEYWORDS)    @ColumnInfo(name = Fields.KEYWORDS)
    var keywords: List<Keyword> = listOf()

)


data class Keyword(

    @SerializedName("id") override      val id: Int,
    @SerializedName("name") override    val name: String

) : NamedIdElement