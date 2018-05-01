package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


data class GuestStar(

    @SerializedName("id") override      val id: Int,
    @SerializedName("name") override    val name: String,
    @SerializedName("credit_id")        val creditId: String,
    @SerializedName("character")        val character: String,
    @SerializedName("order")            val order: Int,
    @SerializedName("profile_path")     val profilePath: String

) : NamedIdElement