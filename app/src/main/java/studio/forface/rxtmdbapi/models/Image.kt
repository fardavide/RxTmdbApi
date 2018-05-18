package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Images (

    @SerializedName(Fields.BACKDROPS)   @ColumnInfo(name = Fields.BACKDROPS)
    var backdrops: List<Image> = listOf(),

    @SerializedName(Fields.POSTERS)     @ColumnInfo(name = Fields.POSTERS)
    var posters:    List<Image> = listOf(),

    @SerializedName(Fields.STILLS)      @ColumnInfo(name = Fields.STILLS)
    var stills:     List<Image> = listOf()

)


data class Image(

    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("file_path")    val filePath: String,
    @SerializedName("height")       val height: Int,
    @SerializedName("iso_639_1")    val iso6391: Any,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count")   val voteCount: Int,
    @SerializedName("width")        val width: Int

)