package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.timeInMillis

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val MOVIE_COLLECTION = "movie_collection"
data class MovieCollection(

    @SerializedName(Fields.ID)              @ColumnInfo(name = "${MOVIE_COLLECTION}_${Fields.ID}")
    override var id: Int = 0,

    @SerializedName(Fields.NAME)            @ColumnInfo(name = "${MOVIE_COLLECTION}_${Fields.NAME}")
    override var name: String = EMPTY_STRING,

    @SerializedName(Fields.OVERVIEW)        @ColumnInfo(name = "${MOVIE_COLLECTION}_${Fields.OVERVIEW}")
    var overview: String = EMPTY_STRING,

    @SerializedName(Fields.POSTER_PATH)     @ColumnInfo(name = "${MOVIE_COLLECTION}_${Fields.POSTER_PATH}")
    var posterPath: String = EMPTY_STRING,

    @SerializedName(Fields.BACKDROP_PATH)   @ColumnInfo(name = "${MOVIE_COLLECTION}_${Fields.BACKDROP_PATH}")
    var backdropPath: String = EMPTY_STRING,

    @SerializedName(Fields.PARTS)           @ColumnInfo(name = "${MOVIE_COLLECTION}_${Fields.PARTS}")
    var parts: List<Part> = listOf()

) : NamedIdElement


data class Part(

        @SerializedName("adult")                val adult: Boolean,
        @SerializedName("backdrop_path")        val backdropPath: String?,
        @SerializedName("genre_ids")            val genreIds: List<Int>,
        @SerializedName("id") override          val id: Int,
        @SerializedName("original_language")    val originalLanguage: String,
        @SerializedName("original_title")       val originalTitle: String,
        @SerializedName("overview")             val overview: String,
        @SerializedName("release_date") private val _releaseDate: String,
        @SerializedName("poster_path")          val posterPath: String,
        @SerializedName("popularity")           val popularity: Double,
        @SerializedName("title")                val title: String,
        @SerializedName("video")                val video: Boolean,
        @SerializedName("vote_average")         val voteAverage: Double,
        @SerializedName("vote_count")           val voteCount: Int

) : IdElement {

    val releaseDate get() = _releaseDate.timeInMillis

}