package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.EMPTY_STRING

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
data class Language(
    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("name") override    val name: String = EMPTY_STRING,
    @SerializedName("english_name")     val englishName: String? = null
) : NamedElement, Pageable {

    init {
        if ( iso6391.length != 2 )
            throw IllegalArgumentException( "iso6391 should have a length of 2" )
    }
    override fun toString() = iso6391

}