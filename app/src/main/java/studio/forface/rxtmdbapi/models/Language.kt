package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

data class Language(

    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("name") override    val name: String,
    @SerializedName("english_name")     val englishName: String

) : NamedElement, Pageable