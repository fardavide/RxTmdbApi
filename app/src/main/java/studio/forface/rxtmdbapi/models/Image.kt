package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.EMPTY_STRING


data class Images (

    @SerializedName(Fields.BACKDROPS)   @ColumnInfo(name = Fields.BACKDROPS)
    var backdrops: List<Image> = listOf(),

    @SerializedName(Fields.POSTERS)     @ColumnInfo(name = Fields.POSTERS)
    var posters:    List<Image> = listOf(),

    @SerializedName(Fields.PROFILES)    @ColumnInfo(name = Fields.PROFILES)
    var profiles:   List<Image> = listOf(),

    @SerializedName(Fields.LOGOS)       @ColumnInfo(name = Fields.LOGOS)
    var logos:      List<Image> = listOf(),

    @SerializedName(Fields.STILLS)      @ColumnInfo(name = Fields.STILLS)
    var stills:     List<Image> = listOf()

) {

    val all get() =
            backdrops + posters + profiles + logos + stills

}


data class Image(

    @SerializedName(Fields.ASPECT_RATIO)    @ColumnInfo(name = Fields.ASPECT_RATIO)
    var aspectRatio: Double = 0.0,

    @SerializedName(Fields.FILE_PATH)       @ColumnInfo(name = Fields.FILE_PATH)
    var filePath: String = EMPTY_STRING,

    @SerializedName(Fields.FILE_TYPE)       @ColumnInfo(name = Fields.FILE_TYPE)
    var fileType: String = EMPTY_STRING,

    @SerializedName(Fields.HEIGHT)          @ColumnInfo(name = Fields.HEIGHT)
    var height: Int = 0,

    @SerializedName(Fields.ISO_639_1)       @ColumnInfo(name = Fields.ISO_639_1)
    var iso6391: String = EMPTY_STRING,

    @SerializedName(Fields.VOTE_AVERAGE)    @ColumnInfo(name = Fields.VOTE_AVERAGE)
    var voteAverage: Double = 0.0,

    @SerializedName(Fields.VOTE_COUNT)      @ColumnInfo(name = Fields.VOTE_COUNT)
    var voteCount: Int = 0,

    @SerializedName(Fields.WIDTH)           @ColumnInfo(name = Fields.WIDTH)
    var width: Int = 0

) : Pageable