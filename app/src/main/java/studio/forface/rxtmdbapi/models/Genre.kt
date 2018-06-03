package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

data class Genres(
    @SerializedName(Fields.GENRES) val genres: List<Genre>
)

data class Genre (
    @SerializedName("id")   override val id: Int,
    @SerializedName("name") override val name: String
): NamedIdElement