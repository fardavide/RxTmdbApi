package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


data class AlternativeTitles (

    @SerializedName("titles")       val titles: List<AlternativeTitle>

)


data class AlternativeTitle (

    @SerializedName("iso_3166_1")   val iso31661: String,
    @SerializedName("title")        val title: String,
    @SerializedName("type")         val type: String?

)