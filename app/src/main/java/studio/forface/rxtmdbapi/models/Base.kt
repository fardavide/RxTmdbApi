package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.Ignore
import com.google.gson.annotations.SerializedName

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

enum class MediaType {
    MOVIE, TV, PERSON
}
open class Media (

    @SerializedName(Fields.MEDIA_TYPE)  @Ignore
    val mediaType: String

) : Pageable