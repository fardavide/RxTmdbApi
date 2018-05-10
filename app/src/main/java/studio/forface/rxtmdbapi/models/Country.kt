package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class Country(

    @SerializedName("iso_3166_1")       val iso31661: String,
    @SerializedName("name") override    val name: String

) : NamedElement