package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
data class Avatar(
    @SerializedName("gravatar") val gravatar: Gravatar
)