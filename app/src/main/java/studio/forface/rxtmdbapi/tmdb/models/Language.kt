package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
data class Language(

    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("name") override    val name: String,
    @SerializedName("english_name")     val englishName: String

) : NamedElement