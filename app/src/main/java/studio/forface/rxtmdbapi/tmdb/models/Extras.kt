package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


enum class Extra {
    CREDITS, IMAGES, KEYWORDS, REVIEWS, VIDEOS
}

class Extras ( vararg extra: Extra) {
    private val _extras = extra.toList().sortedBy { it.name }
    override fun toString() = _extras.joinToString(separator = ",") { it.name.toLowerCase() }
}



data class ExtrasResult<M> (

    @SerializedName("results") val results: List<M>

)