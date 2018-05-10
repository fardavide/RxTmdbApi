package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Credits(

    @SerializedName(Fields.CAST)    @ColumnInfo(name = "${Fields.CREDITS}_${Fields.CAST}")
    var cast: List<Cast> = listOf(),

    @SerializedName(Fields.CREW)    @ColumnInfo(name = "${Fields.CREDITS}_${Fields.CREW}")
    var crew: List<Crew> = listOf()

)