package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


data class Videos (

    @SerializedName("results") val results: List<Video>

)


data class Video(

    @SerializedName("id")               val id: String,
    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("iso_3166_1")       val iso31661: String,
    @SerializedName("key")              val key: String,
    @SerializedName("name") override    val name: String,
    @SerializedName("site")             val site: String,
    @SerializedName("size")             val size: Int,
    @SerializedName("type")             val type: String

) : NamedElement