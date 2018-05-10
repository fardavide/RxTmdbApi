package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.timeInMillis


private const val EXPECTED_RELEASE = "expected_release"
data class ExpectedDate @JvmOverloads constructor (

    @SerializedName(Fields.MAXIMUM) @ColumnInfo(name = "${EXPECTED_RELEASE}_${Fields.MAXIMUM}")
    var _maximum: String = EMPTY_STRING,

    @SerializedName(Fields.MINIMUM) @ColumnInfo(name = "${EXPECTED_RELEASE}_${Fields.MINIMUM}")
    var _minimum: String = EMPTY_STRING

) {

    val maximum get() = _maximum.timeInMillis
    val minimum get() = _minimum.timeInMillis

}