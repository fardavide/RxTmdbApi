package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
data class User(
    @SerializedName(Fields.GRAVATAR_HASH)   val gravatarHash: String,
    @SerializedName(Fields.NAME)            val name: String,
    @SerializedName(Fields.USERNAME)        val username: String
)