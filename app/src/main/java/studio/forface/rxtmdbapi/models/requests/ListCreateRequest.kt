@file:Suppress("unused")

package studio.forface.rxtmdbapi.models.requests

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.Language
import studio.forface.rxtmdbapi.tmdb.TmdbApiConfig
import studio.forface.rxtmdbapi.tmdb.TmdbListsV4
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.Sorting

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
data class ListCreateRequest (
    @SerializedName("name")         val name: String? = null,
    @SerializedName("description")  val description: String? = null,
    @SerializedName("language")     val language: Language? = null
)

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 * A request body for CREATE a List of API v4.
 * @see TmdbListsV4.createList
 *
 * @param name the name of your list.
 * @param description the description of your list.
 * @param language the ISO-639-1 variant for your list. Length = 2.
 * @param public the public status of your list.
 * @param region the ISO-3166-1 variant for your list. Length = 2.
 */
data class ListV4CreateRequest (
    @SerializedName("name")         val name: String,
    @SerializedName("description")  val description: String = EMPTY_STRING,
    @SerializedName("iso_639_1")    val language: Language = TmdbApiConfig.language,
    @SerializedName("public")       val public: Boolean = true,
    @SerializedName("iso_3166_1")   val region: String = EMPTY_STRING
)

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 * A request body for UPDATE a List of API v4.
 * @see TmdbListsV4.updateList
 *
 * @param name the name of your list.
 * @param description the description of your list.
 * @param public the public status of your list.
 * @param sortBy sort the result ascending or descending using [Sorting.ListMovieSorting].
 */
data class ListV4UpdateRequest (
    @SerializedName("name")         val name: String,
    @SerializedName("description")  val description: String? = null,
    @SerializedName("public")       val public: Boolean? = null,
    @SerializedName("sort_by")      val sortBy: Sorting.ListMovieSorting? = null
)