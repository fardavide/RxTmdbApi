package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class Cast(

    @SerializedName("cast_id")          val castId: Int,
    @SerializedName("character")        val character: String,
    @SerializedName("credit_id")        val creditId: String,
    @SerializedName("gender")           val gender: Int,
    @SerializedName("id") override      val id: Int,
    @SerializedName("name") override    val name: String,
    @SerializedName("order")            val order: Int,
    @SerializedName("profile_path")     val profilePath: String?

) : NamedIdElement