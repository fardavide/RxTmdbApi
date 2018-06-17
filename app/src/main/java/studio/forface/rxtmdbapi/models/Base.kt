package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.equalsNoCase

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
interface IdElement {
    val id: Int
}

interface StringIdElement {
    val id: String
}

interface NamedElement {
    val name: String
}

interface NamedIdElement: IdElement, NamedElement

interface NamedStringIdElement: StringIdElement, NamedElement


interface Pageable

enum class MediaType( val string: String ) {
    MOVIE(      "movie"),
    TV_SHOW(    "tv"),
    PERSON(     "person" );
    override fun toString() = string
    companion object {
        operator fun get( string: String ) =
                MediaType.values().find { it.string equalsNoCase string }
    }
}
open class Media (

    @SerializedName(Fields.MEDIA_TYPE)  @Ignore
    val mediaType: String

) : Pageable