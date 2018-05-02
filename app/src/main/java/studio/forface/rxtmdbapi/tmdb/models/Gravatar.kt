package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
data class Gravatar(
    @SerializedName("hash") val hash: String
)