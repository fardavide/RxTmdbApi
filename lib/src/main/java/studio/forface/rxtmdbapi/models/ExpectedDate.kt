package studio.forface.rxtmdbapi.models

import androidx.room.ColumnInfo
import androidx.room.RoomWarnings
import androidx.room.RoomWarnings.DEFAULT_CONSTRUCTOR
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.timeInMillis


@SuppressWarnings(DEFAULT_CONSTRUCTOR)
data class ExpectedDate @JvmOverloads constructor (

    @SerializedName(Fields.MAXIMUM) @ColumnInfo(name = Fields.MAXIMUM)
    var _maximum: String = EMPTY_STRING,

    @SerializedName(Fields.MINIMUM) @ColumnInfo(name = Fields.MINIMUM)
    var _minimum: String = EMPTY_STRING

) {

    val maximum get() = _maximum.timeInMillis
    val minimum get() = _minimum.timeInMillis

}