package studio.forface.rxtmdbapi.models

import androidx.room.ColumnInfo
import androidx.room.RoomWarnings
import androidx.room.RoomWarnings.DEFAULT_CONSTRUCTOR
import com.google.gson.annotations.SerializedName

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
@SuppressWarnings(DEFAULT_CONSTRUCTOR)
data class Keywords(

    @SerializedName(Fields.KEYWORDS)    @ColumnInfo(name = Fields.KEYWORDS)
    var keywords: List<Keyword> = listOf()

)


data class Keyword(

    @SerializedName("id") override      val id: Int,
    @SerializedName("name") override    val name: String

) : NamedIdElement