package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("description")      val description: String,
    @SerializedName("favorite_count")   val favoriteCount: Int,
    @SerializedName("id") override      val id: Int,
    @SerializedName("item_count")       val itemCount: Int,
    @SerializedName("iso_639_1")        val iso6391: String,
    @SerializedName("list_type")        val listType: String,
    @SerializedName("name") override    val name: String,
    @SerializedName("poster_path")      val posterPath: String?

) : NamedIdElement, Pageable