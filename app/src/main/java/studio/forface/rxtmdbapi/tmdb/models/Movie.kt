package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.plus
import studio.forface.rxtmdbapi.utils.timeInMillis


data class Movie(

        @SerializedName("adult")                    val adult: Boolean,
        @SerializedName("backdrop_path")            val backdropPath: String,
        @SerializedName("belongs_to_collection")    val belongsToCollection: Any,
        @SerializedName("budget")                   val budget: Int,
        @SerializedName("genres")                   val genres: List<Genre>,
        @SerializedName("homepage")                 val homepage: String?,
        @SerializedName("id") override              val id: Int,
        @SerializedName("imdb_id")                  val imdbId: String,
        @SerializedName("original_language")        val originalLanguage: String,
        @SerializedName("original_title")           val originalTitle: String,
        @SerializedName("overview")                 val overview: String,
        @SerializedName("popularity")               val popularity: Double,
        @SerializedName("poster_path")              val posterPath: String,
        @SerializedName("production_companies")     val productionCompanies: List<ProductionCompany>,
        @SerializedName("production_countries")     val productionCountries: List<ProductionCountry>,
        @SerializedName("release_date") private     val _releaseDate: String,
        @SerializedName("revenue")                  val revenue: Int,
        @SerializedName("runtime")                  val runtime: Int,
        @SerializedName("spoken_languages")         val languages: List<Language>,
        @SerializedName("status")                   val status: String,
        @SerializedName("tagline")                  val tagline: String,
        @SerializedName("title")                    val title: String,
        @SerializedName("video")                    val video: Boolean,
        @SerializedName("vote_average")             val voteAverage: Double,
        @SerializedName("vote_count")               val voteCount: Int,
        @SerializedName("dates")                    val expectedRelease: Dates,

        @SerializedName("credits") private          val _credits: Credits?,
        @SerializedName("images") private           val _images: Images?,
        @SerializedName("keywords") private         val _keywords: Keywords?,
        @SerializedName("reviews")                  val reviews: ResultPage<Review>?,
        @SerializedName("videos") private           val _videos: Videos?

) : NamedIdElement, Pageable {

    override val name: String get() = title
    val releaseDate get() = _releaseDate.timeInMillis

    val cast        get() = _credits?.cast
    val crew        get() = _credits?.crew
    val allPeople   get() = cast + crew
    val backdrops   get() = _images?.backdrops
    val posters     get() = _images?.posters
    val keywords    get() = _keywords?.keywords
    val videos      get() = _videos?.results

}