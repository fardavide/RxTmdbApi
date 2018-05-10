@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.ModelTypeConverters
import studio.forface.rxtmdbapi.models.Movie.Companion.TABLE_NAME
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.plus
import studio.forface.rxtmdbapi.utils.timeInMillis


@Entity(tableName = TABLE_NAME)
@TypeConverters( ModelTypeConverters::class )
data class Movie (

        @SerializedName(Fields.ID)                      @ColumnInfo(name = Fields.ID)
        @PrimaryKey
        override var id: Int = 0,

        @SerializedName(Fields.ADULT)                   @ColumnInfo(name = Fields.ADULT)
        var adult: Boolean = false,

        @SerializedName(Fields.BACKDROP_PATH)           @ColumnInfo(name = Fields.BACKDROP_PATH)
        var backdropPath: String = EMPTY_STRING,

        @SerializedName(Fields.BELONGS_TO_COLLECTION)   @Embedded
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

        @SerializedName(Fields.DATES)                   @Embedded
        var expectedRelease: ExpectedDate = ExpectedDate(),


        @SerializedName(Fields.ALTERNATIVE_TITLES)      @Ignore
        var _alternativeTitles: AlternativeTitles = AlternativeTitles(),

        @SerializedName(Fields.CHANGES)                 @Embedded
        var _changes: ChangeList = ChangeList(),

        @SerializedName(Fields.CREDITS)                 @Embedded
        var _credits: Credits = Credits(),

        @SerializedName(Fields.EXTERNAL_IDS)            @Embedded
        var externalIds: ExternalIds = ExternalIds(),

        @SerializedName(Fields.IMAGES)                  @Embedded
        var _images: Images = Images(),

        @SerializedName(Fields.KEYWORDS)                @Embedded
        var _keywords: Keywords = Keywords(),

        @SerializedName(Fields.LISTS)                   @Ignore
        var lists: ResultPage<MovieList>? = null,

        @SerializedName(Fields.RECOMMENDATIONS)         @Ignore
        var recommendations: ResultPage<Movie>? = null,

        @SerializedName(Fields.RELEASE_DATES)           @Embedded
        var releaseDates: ReleaseDates = ReleaseDates(),

        @SerializedName(Fields.REVIEWS)                 @Ignore
        var reviews: ResultPage<Review>? = null,

        @SerializedName(Fields.SIMILAR)                 @Ignore
        var similar: ResultPage<Movie>? = null,

        @SerializedName(Fields.TRANSLATIONS)            @Ignore
        var _translations: Translations<Movie> = Translations(),

        @SerializedName(Fields.VIDEOS)                  @Embedded
        var _videos: Videos = Videos()

) : NamedIdElement, Pageable {

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
            _changes = ChangeList(),
            _credits = Credits(),
            externalIds = ExternalIds(),
            _images = Images(),
            _keywords = Keywords(),
            releaseDates = ReleaseDates(),
            _videos = Videos()
    )

}