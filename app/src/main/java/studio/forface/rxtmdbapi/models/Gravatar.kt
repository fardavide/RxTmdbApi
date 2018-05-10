package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName
data class Gravatar(
    @SerializedName("hash") val hash: String
)