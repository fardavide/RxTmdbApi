package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName

data class Network(

    @SerializedName("headquarters")     val headquarters: String,
    @SerializedName("homepage")         val homepage: String,
    @SerializedName("id") override      val id: Int,
    @SerializedName("name") override    val name: String,
    @SerializedName("origin_country")   val originCountry: String

) : NamedIdElement