package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis


data class TvEpisode (

    @SerializedName("air_date") private val _airDate: String,
    @SerializedName("crew")             val crew: List<Crew>,
    @SerializedName("episode_number")   val episodeNumber: Int,
    @SerializedName("guest_stars")      val guestStars: List<GuestStar>,
    @SerializedName("name") override    val name: String,
    @SerializedName("overview")         val overview: String,
    @SerializedName("id") override      val id: Int,
    @SerializedName("production_code")  val productionCode: String,
    @SerializedName("season_number")    val seasonNumber: Int,
    @SerializedName("still_path")       val stillPath: String,
    @SerializedName("vote_average")     val voteAverage: Double,
    @SerializedName("vote_count")       val voteCount: Int

) : NamedIdElement, Pageable {

    val airDate get() = _airDate.timeInMillis

}


data class TvEpisodeReferences (

    @SerializedName("id") override      val id: Int,
    @SerializedName("results")          val episodeReferences: List<TvEpisodeReference>

) : IdElement


data class TvEpisodeReference (

    @SerializedName("id") override      val id: Int,
    @SerializedName("episode_number")   val episodeNumber: Int,
    @SerializedName("season_number")    val seasonNumber: Int

) : IdElement