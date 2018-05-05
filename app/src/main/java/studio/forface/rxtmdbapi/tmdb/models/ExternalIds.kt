package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


data class ExternalIds(

    @SerializedName("imdb_id")      val imdbId: String,
    @SerializedName("facebook_id")  val facebookId: String,
    @SerializedName("instagram_id") val instagramId: String,
    @SerializedName("twitter_id")   val twitterId: String,
    @SerializedName("id")           val id: Int

)