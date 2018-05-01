package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
data class ProductionCountry(

    @SerializedName("iso_3166_1")       val iso31661: String,
    @SerializedName("name") override    val name: String

) : NamedElement