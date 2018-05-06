package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

data class MovieCollection(

    @SerializedName("id") override              val id: Int,
    @SerializedName("name") override            val name: String,
    @SerializedName("overview")                 val overview: String,
    @SerializedName("poster_path")              val posterPath: String?,
    @SerializedName("backdrop_path")            val backdropPath: String?,
    @SerializedName("parts")                    val parts: List<Part>

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