package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.MovieCollection.Companion.TABLE_NAME
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.ModelTypeConverters
import studio.forface.rxtmdbapi.utils.timeInMillis

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Entity(tableName = TABLE_NAME)
@TypeConverters( ModelTypeConverters::class )
data class MovieCollection(

    @SerializedName(Fields.ID)              @ColumnInfo(name = Fields.ID)
    @PrimaryKey
    override var id: Int = 0,

    @SerializedName(Fields.NAME)            @ColumnInfo(name = Fields.NAME)
    override var name: String = EMPTY_STRING,

    @SerializedName(Fields.OVERVIEW)        @ColumnInfo(name = Fields.OVERVIEW)
    var overview: String = EMPTY_STRING,

    @SerializedName(Fields.POSTER_PATH)     @ColumnInfo(name = Fields.POSTER_PATH)
    var posterPath: String = EMPTY_STRING,

    @SerializedName(Fields.BACKDROP_PATH)   @ColumnInfo(name = Fields.BACKDROP_PATH)
    var backdropPath: String = EMPTY_STRING,

    @SerializedName(Fields.PARTS)           @ColumnInfo(name = Fields.PARTS)
    var parts: List<Part> = listOf()

) : NamedIdElement, Pageable {

    companion object {
        internal const val TABLE_NAME = "movie_collections"
    }

}


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

) : IdElement, Pageable {

    val releaseDate get() = _releaseDate.timeInMillis

}