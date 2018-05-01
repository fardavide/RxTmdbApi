package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


data class Keywords(

    @SerializedName("keywords") val keywords: List<Keyword>

)


data class Keyword(

    @SerializedName("id") override      val id: Int,
    @SerializedName("name") override    val name: String

) : NamedIdElement