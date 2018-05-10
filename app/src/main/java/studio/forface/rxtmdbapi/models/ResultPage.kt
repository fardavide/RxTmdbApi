package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class ResultPage<S: Pageable> (

    @SerializedName("page")            val pageIndex: Int,
    @SerializedName("total_results")   val resultsCount: Int,
    @SerializedName("total_pages")     val totalPagesCount: Int,
    @SerializedName("results")         val results: List<S>

)