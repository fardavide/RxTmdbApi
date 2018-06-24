package studio.forface.rxtmdbapi.models

import androidx.room.ColumnInfo
import androidx.room.RoomWarnings.DEFAULT_CONSTRUCTOR
import com.google.gson.annotations.SerializedName

@SuppressWarnings(DEFAULT_CONSTRUCTOR)
data class Credits(

    @SerializedName(Fields.CAST)    @ColumnInfo(name = Fields.CAST)
    var cast: List<Cast> = listOf(),

    @SerializedName(Fields.CREW)    @ColumnInfo(name = Fields.CREW)
    var crew: List<Crew> = listOf()

)