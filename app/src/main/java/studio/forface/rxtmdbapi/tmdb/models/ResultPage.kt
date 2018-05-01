package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


class ResultPage<S: Pageable> (

    @SerializedName("page")            val pageIndex: Int,
    @SerializedName("total_results")   val resultsCount: Int,
    @SerializedName("total_pages")     val totalPagesCount: Int,
    @SerializedName("results")         val results: List<S>

)