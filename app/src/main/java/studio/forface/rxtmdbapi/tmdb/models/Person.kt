package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis

data class Person(

    @SerializedName("adult")            val adult: Boolean,
    @SerializedName("also_known_as")    val alsoKnownAs: List<Any>?,
    @SerializedName("biography")        val biography: String?,
    @SerializedName("birthday") private val _birthday: String?,
    @SerializedName("deathday") private val _deathday: String?,
    @SerializedName("gender")           val gender: Int,
    @SerializedName("homepage")         val homepage: String?,
    @SerializedName("id") override      val id: Int,
    @SerializedName("imdb_id")          val imdbId: String?,
    @SerializedName("name") override    val name: String,
    @SerializedName("place_of_birth")   val placeOfBirth: String?,
    @SerializedName("popularity")       val popularity: Double,
    @SerializedName("profile_path")     val profilePath: String

) : NamedIdElement, Pageable {

    val birthday get() = _birthday.timeInMillis
    val deathday get() = _deathday.timeInMillis

}