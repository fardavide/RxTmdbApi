@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package studio.forface.rxtmdbapi.models

import androidx.room.*
import androidx.room.RoomWarnings.DEFAULT_CONSTRUCTOR
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.Movie.Companion.TABLE_NAME
import studio.forface.rxtmdbapi.utils.*

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
@Entity(tableName = TABLE_NAME)
@TypeConverters( ModelTypeConverters::class )
@SuppressWarnings(DEFAULT_CONSTRUCTOR)
data class Movie (

    @SerializedName(Fields.ID)                      @ColumnInfo(name = Fields.ID)
    @PrimaryKey //@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    override var id: Int = 0,

    /* Local value */                               @ColumnInfo(name = Fields.TIMESTAMP)
    var timestamp: Long = now,

    @SerializedName(Fields.ADULT)                   @ColumnInfo(name = Fields.ADULT)
    var adult: Boolean = false,

    @SerializedName(Fields.BACKDROP_PATH)           @ColumnInfo(name = Fields.BACKDROP_PATH)
    var backdropPath: String = EMPTY_STRING,

    @SerializedName(Fields.BELONGS_TO_COLLECTION)   @Embedded(prefix = Fields.BELONGS_TO_COLLECTION)
    var belongsToCollection: MovieCollection = MovieCollection(),

    @SerializedName(Fields.BUDGET)                  @ColumnInfo(name = Fields.BUDGET)
    var budget: Int = 0,

    @SerializedName(Fields.GENRES)                  @ColumnInfo(name = Fields.GENRES)
    var genres: List<Genre> = listOf(),

    @SerializedName(Fields.HOMEPAGE)                @ColumnInfo(name = Fields.HOMEPAGE)
    var homepage: String = EMPTY_STRING,

    @SerializedName(Fields.IMDB_ID)                 @ColumnInfo(name = Fields.IMDB_ID)
    var imdbId: String = EMPTY_STRING,

    @SerializedName(Fields.ORIGINAL_LANGUAGE)       @ColumnInfo(name = Fields.ORIGINAL_LANGUAGE)
    var originalLanguage: String = EMPTY_STRING,

    @SerializedName(Fields.ORIGINAL_TITLE)          @ColumnInfo(name = Fields.ORIGINAL_TITLE)
    var originalTitle: String = EMPTY_STRING,

    @SerializedName(Fields.OVERVIEW)                @ColumnInfo(name = Fields.OVERVIEW)
    var overview: String = EMPTY_STRING,

    @SerializedName(Fields.POPULARITY)              @ColumnInfo(name = Fields.POPULARITY)
    var popularity: Double = 0.0,

    @SerializedName(Fields.POSTER_PATH)             @ColumnInfo(name = Fields.POSTER_PATH)
    var posterPath: String = EMPTY_STRING,

    @SerializedName(Fields.PRODUCTION_COMPANIES)    @ColumnInfo(name = Fields.PRODUCTION_COMPANIES)
    var productionCompanies: List<Company> = listOf(),

    @SerializedName(Fields.PRODUCTION_COUNTRIES)    @ColumnInfo(name = Fields.PRODUCTION_COUNTRIES)
    var productionCountries: List<Country> = listOf(),

    @SerializedName(Fields.RELEASE_DATE)            @ColumnInfo(name = Fields.RELEASE_DATE)
    var _releaseDate: String = EMPTY_STRING,

    @SerializedName(Fields.REVENUE)                 @ColumnInfo(name = Fields.REVENUE)
    var revenue: Int = 0,

    @SerializedName(Fields.RUNTIME)                 @ColumnInfo(name = Fields.RUNTIME)
    var runtime: String = EMPTY_STRING,

    @SerializedName(Fields.SPOKEN_LANGUAGES)        @ColumnInfo(name = Fields.SPOKEN_LANGUAGES)
    var languages: List<Language> = listOf(),

    @SerializedName(Fields.STATUS)                  @ColumnInfo(name = Fields.STATUS)
    var status: String = EMPTY_STRING,

    @SerializedName(Fields.TAGLINE)                 @ColumnInfo(name = Fields.TAGLINE)
    var tagline: String = EMPTY_STRING,

    @SerializedName(Fields.TITLE)                   @ColumnInfo(name = Fields.TITLE)
    var title: String = EMPTY_STRING,

    @SerializedName(Fields.VIDEO)                   @ColumnInfo(name = Fields.VIDEO)
    var video: Boolean = false,

    @SerializedName(Fields.VOTE_AVERAGE)            @ColumnInfo(name = Fields.VOTE_AVERAGE)
    var voteAverage: Double = 0.0,

    @SerializedName(Fields.VOTE_COUNT)              @ColumnInfo(name = Fields.VOTE_COUNT)
    var voteCount: Int = 0,

    @SerializedName(Fields.DATES)                   @Embedded(prefix = Fields.DATES)
    var expectedRelease: ExpectedDate = ExpectedDate(),


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

    @SerializedName(Fields.LISTS)                   @Ignore
    var lists: ResultPage<MovieList>? = null,

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


) : Media( MediaType.MOVIE.name ), NamedIdElement, Pageable {

    companion object {
        internal const val TABLE_NAME = "movies"
    }

    override val name: String get() = title
    val releaseDate get() = _releaseDate.timeInMillis

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
            adult = false,
            backdropPath = EMPTY_STRING,
            belongsToCollection = MovieCollection(),
            budget = 0,
            genres = listOf<Genre>(),
            homepage = EMPTY_STRING,
            imdbId = EMPTY_STRING,
            originalLanguage = EMPTY_STRING,
            originalTitle = EMPTY_STRING,
            overview = EMPTY_STRING,
            popularity = 0.0,
            posterPath = EMPTY_STRING,
            productionCompanies = listOf<Company>(),
            productionCountries = listOf<Country>(),
            _releaseDate = EMPTY_STRING,
            revenue = 0,
            runtime = EMPTY_STRING,
            languages = listOf<Language>(),
            status = EMPTY_STRING,
            tagline = EMPTY_STRING,
            title = EMPTY_STRING,
            video = false,
            voteAverage = 0.0,
            voteCount = 0,
            expectedRelease = ExpectedDate(),

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