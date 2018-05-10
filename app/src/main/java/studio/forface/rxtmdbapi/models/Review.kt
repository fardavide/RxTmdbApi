package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class Review (

    @SerializedName("author")   val author: String,
    @SerializedName("content")  val content: String,
    @SerializedName("id")       val id: String,
    @SerializedName("url")      val url: String

) : Pageable