package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class Genre (
    @SerializedName("id")   override val id: Int,
    @SerializedName("name") override val name: String

): NamedIdElement