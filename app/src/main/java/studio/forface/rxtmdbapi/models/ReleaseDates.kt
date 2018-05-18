package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis


data class ReleaseDates (

    @SerializedName(Fields.ID)      @Ignore
    var movieId: Int = 0,

    @SerializedName(Fields.RESULTS) @ColumnInfo(name = Fields.RESULTS)
    var regionReleases: List<RegionRelease> = listOf()

)


data class RegionRelease (

        @SerializedName("iso_3166_1")           val iso31661: String,
        @SerializedName("release_dates")        val releaseDates: List<ReleaseDate>

)


data class ReleaseDate (

        @SerializedName("certification")        val certification: String,
        @SerializedName("iso_639_1")            val iso6391: String,
        @SerializedName("note")                 val note: String?,
        @SerializedName("release_date") private val _date: String,
        @SerializedName("type") private         val _type: Int

) {

    val date get() = _date.timeInMillis
    val type get() = ReleaseType.getter(_type)

}


enum class ReleaseType (val value: Int) {

    PREMIERE            (1),
    THEATRICAL_LIMITED  (2),
    THEATRICAL          (3),
    DIGITAL             (4),
    PHYSICAL            (5),
    TV                  (6);

    companion object {
        val getter: (Int) -> ReleaseType? get() =
                { ReleaseType.values().find { releaseType -> releaseType.value == it } }
    }

}