package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.plus
import studio.forface.rxtmdbapi.utils.timeInMillis

data class TvShow(

        @SerializedName("backdrop_path")                val backdropPath: String,
        @SerializedName("created_by")                   val createdBy: List<Person>,
        @SerializedName("episode_run_time")             val episodeRunTime: List<Int>,
        @SerializedName("first_air_date")               val firstAirDate: String,
        @SerializedName("genres")                       val genres: List<Genre>,
        @SerializedName("homepage")                     val homepage: String,
        @SerializedName("id") override                  val id: Int,
        @SerializedName("in_production")                val inProduction: Boolean,
        @SerializedName("languages")                    val languages: List<String>,
        @SerializedName("last_air_date") private        val _lastAirDate: String,
        @SerializedName("name") override                val name: String,
        @SerializedName("networks")                     val networks: List<Network>,
        @SerializedName("number_of_episodes")           val numberOfEpisodes: Int,
        @SerializedName("number_of_seasons")            val numberOfSeasons: Int,
        @SerializedName("origin_country")               val originCountry: List<String>,
        @SerializedName("original_language")            val originalLanguage: String,
        @SerializedName("original_name")                val originalName: String,
        @SerializedName("overview")                     val overview: String,
        @SerializedName("popularity")                   val popularity: Double,
        @SerializedName("poster_path")                  val posterPath: String,
        @SerializedName("production_companies")         val productionCompanies: List<Company>,
        @SerializedName("seasons")                      val seasons: List<Season>,
        @SerializedName("status")                       val status: String,
        @SerializedName("type")                         val type: String,
        @SerializedName("vote_average")                 val voteAverage: Double,
        @SerializedName("vote_count")                   val voteCount: Int,

        @SerializedName("alternative_titles") private   val _alternativeTitles: AlternativeTitles?,
        @SerializedName("changes") private              val _changes: ChangeList?,
        @SerializedName("credits") private              val _credits: Credits?,
        @SerializedName("external_ids")                 val externalIds: ExternalIds?,
        @SerializedName("images") private               val _images: Images?,
        @SerializedName("keywords") private             val _keywords: Keywords?,
        @SerializedName("recommendations")              val recommendations: ResultPage<TvShow>?,
        @SerializedName("release_dates")                val releaseDates: ReleaseDates?,
        @SerializedName("reviews")                      val reviews: ResultPage<Review>?,
        @SerializedName("similar")                      val similar: ResultPage<TvShow>?,
        @SerializedName("translations") private         val _translations: Translations<TvShow>?,
        @SerializedName("videos") private               val _videos: Videos?

) : NamedIdElement, Pageable {

    val lastAirDate         get() = _lastAirDate.timeInMillis

    val alternativeTitles   get() = _alternativeTitles?.titles
    val changes             get() = _changes?.changes
    val cast                get() = _credits?.cast
    val crew                get() = _credits?.crew
    val allPeople           get() =  cast + crew
    val backdrops           get() = _images?.backdrops
    val posters             get() = _images?.posters
    val keywords            get() = _keywords?.keywords
    val translations        get() = _translations?.translations
    val videos              get() = _videos?.results

}