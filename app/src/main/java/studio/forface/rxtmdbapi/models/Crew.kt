package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class Crew(

    @SerializedName("id") override      val id: Int,
    @SerializedName("credit_id")        val creditId: String,
    @SerializedName("name") override    val name: String,
    @SerializedName("department")       val department: String,
    @SerializedName("job")              val job: String,
    @SerializedName("profile_path")     val profilePath: String?

) : NamedIdElement