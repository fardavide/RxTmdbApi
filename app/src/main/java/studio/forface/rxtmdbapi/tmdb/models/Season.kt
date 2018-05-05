package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName

data class Season(

        @SerializedName("_id")              val stringId: String,
        @SerializedName("air_date")         val airDate: String,
        @SerializedName("episodes")         val episodes: List<TvEpisode>,
        @SerializedName("name") override    val name: String,
        @SerializedName("overview")         val overview: String,
        @SerializedName("id") override      val id: Int,
        @SerializedName("poster_path")      val posterPath: String,
        @SerializedName("season_number")    val seasonNumber: Int

) : NamedIdElement