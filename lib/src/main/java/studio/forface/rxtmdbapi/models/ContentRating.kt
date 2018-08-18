package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class ContentRatings (

    @SerializedName("results")                      val ratings: List<ContentRating.Short>

)


interface ContentRating {

    val rating: String

    data class Full (

        @SerializedName("certification") override   val rating: String,
        @SerializedName("meaning")                  val meaning: String,
        @SerializedName("order")                    val order: Int

    ) : ContentRating

    data class Short (

        @SerializedName("rating") override          val rating: String,
        @SerializedName("iso_3166_1")               val iso31661: String

    ) : ContentRating

}