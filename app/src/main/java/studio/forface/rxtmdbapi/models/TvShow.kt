@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.ModelTypeConverters
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.now
import studio.forface.rxtmdbapi.utils.plus
import studio.forface.rxtmdbapi.utils.timeInMillis

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Entity(tableName = TvShow.TABLE_NAME)
@TypeConverters( ModelTypeConverters::class )
data class TvShow(

    @SerializedName(Fields.ID)                      @ColumnInfo(name = Fields.ID)
    @PrimaryKey
    override var id: Int = 0,

    /* Local value */                               @ColumnInfo(name = Fields.TIMESTAMP)
    var timestamp: Long = now,

    @SerializedName(Fields.BACKDROP_PATH)           @ColumnInfo(name = Fields.BACKDROP_PATH)
    var backdropPath: String = EMPTY_STRING,

    @SerializedName(Fields.CREATED_BY)              @ColumnInfo(name = Fields.CREATED_BY)
    var createdBy: List<Person> = listOf(),

    @SerializedName(Fields.EPISODE_RUN_TIME)        @ColumnInfo(name = Fields.EPISODE_RUN_TIME)
    var episodeRunTime: List<Int> = listOf(),

    @SerializedName(Fields.FIRST_AIR_DATE)          @ColumnInfo(name = Fields.FIRST_AIR_DATE)
    var firstAirDate: String = EMPTY_STRING,

    @SerializedName(Fields.GENRES)                  @ColumnInfo(name = Fields.GENRES)
    var genres: List<Genre> = listOf(),

    @SerializedName(Fields.HOMEPAGE)                @ColumnInfo(name = Fields.HOMEPAGE)
    var homepage: String = EMPTY_STRING,

    @SerializedName(Fields.IN_PRODUCTION)           @ColumnInfo(name = Fields.IN_PRODUCTION)
    var inProduction: Boolean = false,

    @SerializedName(Fields.LANGUAGES)               @ColumnInfo(name = Fields.LANGUAGES)
    var languages: List<String> = listOf(),

    @SerializedName(Fields.LAST_AIR_DATE)           @ColumnInfo(name = Fields.LAST_AIR_DATE)
    var _lastAirDate: String = EMPTY_STRING,

    @SerializedName(Fields.NAME)                    @ColumnInfo(name = Fields.NAME)
    override var name: String = EMPTY_STRING,

    @SerializedName(Fields.NETWORKS)                @ColumnInfo(name = Fields.NETWORKS)
    var networks: List<Network> = listOf(),

    @SerializedName(Fields.NUMBER_OF_EPISODES)      @ColumnInfo(name = Fields.NUMBER_OF_EPISODES)
    var numberOfEpisodes: Int = 0,

    @SerializedName(Fields.NUMBER_OF_SEASONS)       @ColumnInfo(name = Fields.NUMBER_OF_SEASONS)
    var numberOfSeasons: Int = 0,

    @SerializedName(Fields.ORIGIN_COUNTRY)          @ColumnInfo(name = Fields.ORIGIN_COUNTRY)
    var originCountry: List<String> = listOf(),

    @SerializedName(Fields.ORIGINAL_LANGUAGE)       @ColumnInfo(name = Fields.ORIGINAL_LANGUAGE)
    var originalLanguage: String = EMPTY_STRING,

    @SerializedName(Fields.ORIGINAL_NAME)           @ColumnInfo(name = Fields.ORIGINAL_NAME)
    var originalName: String = EMPTY_STRING,

    @SerializedName(Fields.OVERVIEW)                @ColumnInfo(name = Fields.OVERVIEW)
    var overview: String = EMPTY_STRING,

    @SerializedName(Fields.POPULARITY)              @ColumnInfo(name = Fields.POPULARITY)
    var popularity: Double = 0.0,

    @SerializedName(Fields.POSTER_PATH)             @ColumnInfo(name = Fields.POSTER_PATH)
    var posterPath: String = EMPTY_STRING,

    @SerializedName(Fields.PRODUCTION_COMPANIES)    @ColumnInfo(name = Fields.PRODUCTION_COMPANIES)
    var productionCompanies: List<Company> = listOf(),

    @SerializedName(Fields.SEASONS)                 @ColumnInfo(name = Fields.SEASONS)
    var seasons: List<Season> = listOf(),

    @SerializedName(Fields.STATUS)                  @ColumnInfo(name = Fields.STATUS)
    var status: String = EMPTY_STRING,

    @SerializedName(Fields.TYPE)                    @ColumnInfo(name = Fields.TYPE)
    var type: String = EMPTY_STRING,

    @SerializedName(Fields.VOTE_AVERAGE)            @ColumnInfo(name = Fields.VOTE_AVERAGE)
    var voteAverage: Double = 0.0,

    @SerializedName(Fields.VOTE_COUNT)              @ColumnInfo(name = Fields.VOTE_COUNT)
    var voteCount: Int = 0,


    // OPTIONALS.

    @SerializedName(Fields.ALTERNATIVE_TITLES)      @Ignore
    var _alternativeTitles: AlternativeTitles = AlternativeTitles(),

    @SerializedName(Fields.CHANGES)                 @Embedded(prefix = Fields.CHANGES)
    var _changes: ChangeList = ChangeList(),

    @SerializedName(Fields.CREDITS)                 @Embedded(prefix = Fields.CREDITS)
    var _credits: Credits = Credits(),

    @SerializedName(Fields.EXTERNAL_IDS)            @Embedded(prefix = Fields.EXTERNAL_IDS)
    var externalIds: ExternalIds = ExternalIds(),

    @SerializedName(Fields.IMAGES)                  @Embedded(prefix = Fields.IMAGES)
    var _images: Images = Images(),

    @SerializedName(Fields.KEYWORDS)                @Embedded(prefix = Fields.KEYWORDS)
    var _keywords: Keywords = Keywords(),

    @SerializedName(Fields.RECOMMENDATIONS)         @Ignore
    var recommendations: ResultPage<Movie>? = null,

    @SerializedName(Fields.RELEASE_DATES)           @Embedded(prefix = Fields.RELEASE_DATES)
    var releaseDates: ReleaseDates = ReleaseDates(),

    @SerializedName(Fields.REVIEWS)                 @Ignore
    var reviews: ResultPage<Review>? = null,

    @SerializedName(Fields.SIMILAR)                 @Ignore
    var similar: ResultPage<Movie>? = null,

    @SerializedName(Fields.TRANSLATIONS)            @Ignore
    var _translations: Translations<Movie> = Translations(),

    @SerializedName(Fields.VIDEOS)                  @Embedded(prefix = Fields.VIDEOS)
    var _videos: Videos = Videos()


) : Media( MediaType.TV.name ), NamedIdElement, Pageable {

    companion object {
        internal const val TABLE_NAME = "tv_shows"
    }

    val lastAirDate         get() = _lastAirDate.timeInMillis

    val alternativeTitles   get() = _alternativeTitles.titles
    val changes             get() = _changes.changes
    val cast                get() = _credits.cast
    val crew                get() = _credits.crew
    val allPeople           get() =  cast + crew
    val backdrops           get() = _images.backdrops
    val posters             get() = _images.posters
    val keywords            get() = _keywords.keywords
    val translations        get() = _translations.translations
    val videos              get() = _videos.results

    constructor() : this(
            id = 0,
            timestamp = now,
            backdropPath = EMPTY_STRING,
            createdBy = listOf(),
            episodeRunTime = listOf(),
            firstAirDate = EMPTY_STRING,
            genres = listOf(),
            homepage = EMPTY_STRING,
            inProduction = false,
            languages = listOf(),
            _lastAirDate = EMPTY_STRING,
            name = EMPTY_STRING,
            networks = listOf(),
            numberOfEpisodes = 0,
            numberOfSeasons = 0,
            originCountry = listOf(),
            originalLanguage = EMPTY_STRING,
            originalName = EMPTY_STRING,
            overview = EMPTY_STRING,
            popularity = 0.0,
            posterPath = EMPTY_STRING,
            productionCompanies = listOf(),
            seasons = listOf(),
            status = EMPTY_STRING,
            type = EMPTY_STRING,
            voteAverage = 0.0,
            voteCount = 0,

            // Optionals.
            _changes = ChangeList(),
            _credits = Credits(),
            externalIds = ExternalIds(),
            _images = Images(),
            _keywords = Keywords(),
            releaseDates = ReleaseDates(),
            _videos = Videos()
    )

}