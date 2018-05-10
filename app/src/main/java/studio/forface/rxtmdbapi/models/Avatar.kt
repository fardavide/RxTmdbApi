package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName
data class Avatar(
    @SerializedName("gravatar") val gravatar: Gravatar
)