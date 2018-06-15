package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
data class ResultPage<S: Pageable> (
    @SerializedName("page")            val pageIndex: Int,
    @SerializedName("total_results")   val resultsCount: Int,
    @SerializedName("total_pages")     val totalPagesCount: Int,
    @SerializedName(Fields.RESULTS)            val results: List<S>
)

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
data class ListPage (
    @SerializedName("page")            val pageIndex: Int,
    @SerializedName("total_results")   val resultsCount: Int,
    @SerializedName("total_pages")     val totalPagesCount: Int,
    @SerializedName(Fields.RESULTS)            val results: List<Media>,

    @SerializedName(Fields.ID) override        val id: Int,
    @SerializedName(Fields.NAME) override      val name: String,
    @SerializedName(Fields.DESCRIPTION)        val description: String,
    @SerializedName(Fields.CREATED_BY)         val creator: User,
    @SerializedName(Fields.ISO_639_1)          val language: String,
    @SerializedName(Fields.ISO_3166_1)         val region: String,

    @SerializedName(Fields.POSTER_PATH)        val posterPath: String,
    @SerializedName(Fields.BACKDROP_PATH)      val backdropPath: String,
    @SerializedName(Fields.PUBLIC)             val public: Boolean,
    @SerializedName(Fields.REVENUE)            val revenue: String,
    @SerializedName(Fields.AVERAGE_RATING)     val averageRating: Float,
    @SerializedName(Fields.RUNTIME)            val runtime: Int,

    @SerializedName("object_ids")      val mediaIds: Map<String, String>,
    @SerializedName("comments")        val comments: Map<String, String?>
) : NamedIdElement