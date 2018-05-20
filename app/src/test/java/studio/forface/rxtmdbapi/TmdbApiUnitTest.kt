package studio.forface.rxtmdbapi

import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Test
import studio.forface.rxtmdbapi.models.Extra.*
import studio.forface.rxtmdbapi.models.Extras
import studio.forface.rxtmdbapi.tmdb.TmdbApi
import studio.forface.rxtmdbapi.tmdb.removeMovieFromFavorite
import studio.forface.rxtmdbapi.tmdb.removeMovieFromWatchlist
import studio.forface.rxtmdbapi.utils.DateQuery
import studio.forface.rxtmdbapi.utils.Sorting

private const val MOVIE_ID_BLADE = 335984
private const val NETWORK_ID_RANDOM = "213"
private const val PERSON_ID_DICAPRIO = 6193
private const val TV_SHOW_ID_SIMPSON = 456

class TmdbApiUnitTest {

    private val tmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, SESSION_ID )
    }

    private val tmdbAuth            get() = tmdbApi.auth
    private val tmdbAccount         get() = tmdbApi.account
    private val tmdbCertifications  get() = tmdbApi.certifications
    private val tmdbChanges         get() = tmdbApi.changes
    private val tmdbCollections     get() = tmdbApi.collections
    private val tmdbConfig          get() = tmdbApi.config
    private val tmdbMovies          get() = tmdbApi.movies
    private val tmdbNetworks        get() = tmdbApi.networks
    private val tmdbPeople          get() = tmdbApi.people
    private val tmdbReviews         get() = tmdbApi.reviews
    private val tmdbSearch          get() = tmdbApi.search
    private val tmdbTvShows         get() = tmdbApi.tvShows
    private val tmdbTvSeasons       get() = tmdbApi.tvSeasons
    private val tmdbTvEpisodes      get() = tmdbApi.tvEpisodes


    // Auth.
    @Test fun createSession() {
        tmdbAuth.run {
            preloadToken().blockingAwait()

            //val guestSession = createGuessSession().blockingGet()
            val userSession = createUserSessionWithLogin( USERNAME, PASSWORD ).blockingGet()

            println( userSession )
        }
    }

    // Account.
    @Test fun getAccountDetails() {
        //tmdbAuth.createUserSessionWithLogin( USERNAME, PASSWORD ).blockingGet()

        val account = tmdbAccount.getDetails()
                .blockingGet()

        println( account )
    }

    @Test fun getCreatedLists() {
        val page = tmdbAccount.getCreatedLists()
                .blockingGet()

        println( page )
    }

    @Test fun getFavorite() {
        val page = tmdbAccount.getFavoriteTvShows( sortBy = Sorting.CreationDate.ASCENDING )
                .blockingGet()

        println( page )
    }

    @Test fun getRated() {
        val page = tmdbAccount.getRatedTvEpisodes( sortBy = Sorting.CreationDate.DESCENDING )
                .blockingGet()

        println( page )
    }

    @Test fun getWatchlist() {
        val page = tmdbAccount.getMoviesWatchlist()
                .blockingGet()

        println( page )
    }

    @Test fun manageWatchlist() {
        val response = tmdbAccount.removeMovieFromWatchlist(335984)
                .blockingGet()

        println( "completed ${response.string()}" )
    }

    @Test fun manageFavorite() {
        val response = tmdbAccount.removeMovieFromFavorite(335984)
                .blockingGet()

        println( "completed ${response.string()}" )
    }

    // Certifications.
    @Test fun getCertifications() {
        val result = tmdbCertifications.getMovieCertifications()
                .blockingGet()

        println( result.certifications )
    }

    // Changes.
    @Test fun getChanges() {
        val page = tmdbChanges.getMovieChanges(
                startDate = DateQuery( 2018, 5, 6 )
        ).blockingGet()

        println( page.resultsCount )
    }

    // Collections.
    @Test fun getCollectionDetails() {
        val collection = tmdbCollections.getDetails(86311)
                .blockingGet()

        println( collection )
    }

    @Test fun getCollectionSomething() {
        val collection = tmdbCollections.getTranslations(86311)
                .blockingGet()

        println( collection )
    }


    // Config.
    @Test fun getLanguages() {
        val languages = tmdbConfig.getLanguages()
                .blockingGet()

        println( languages )
    }

    // Movies.
    @Test fun movies() {
        tmdbMovies.run { testSinglesStream(
                getDetails(             MOVIE_ID_BLADE,
                        extras = Extras( VIDEOS, IMAGES, KEYWORDS, REVIEWS, CREDITS )),
                getAlternativeTitles(   MOVIE_ID_BLADE ),
                getChanges(             MOVIE_ID_BLADE ),
                getCredits(             MOVIE_ID_BLADE ),
                getExternalIds(         MOVIE_ID_BLADE ),
                getImages(              MOVIE_ID_BLADE ),
                getKeywords(            MOVIE_ID_BLADE ),
                getReleaseDates(        MOVIE_ID_BLADE ),
                getVideos(              MOVIE_ID_BLADE ),
                getTranslations(        MOVIE_ID_BLADE ),
                getRecommendations(     MOVIE_ID_BLADE ),
                getSimilar(             MOVIE_ID_BLADE ),
                getReviews(             MOVIE_ID_BLADE ),
                // FIXME: getLists(               MOVIE_ID_BLADE ),
                getLatest(),
                getNowPlaying(),
                getPopular(),
                getTopRated(),
                getUpcoming(),
                rateMovie(          MOVIE_ID_BLADE, 6 ),
                removeMovieRating(  MOVIE_ID_BLADE )
        ) }
    }

    // Networks.
    @Test fun networks() {
        tmdbNetworks.run { testSinglesStream(
                getDetails(             NETWORK_ID_RANDOM ),
                getAlternativeNames(    NETWORK_ID_RANDOM ),
                getImages(              NETWORK_ID_RANDOM )
                        .map { it.all }
        ) }
    }

    // People.
    @Test fun people() {
        tmdbPeople.run { testSinglesStream(
                getDetails(         PERSON_ID_DICAPRIO ),
                getChanges(         PERSON_ID_DICAPRIO ),
                getMovieCredits(    PERSON_ID_DICAPRIO ),
                getTvCredits(       PERSON_ID_DICAPRIO ),
                getCombinedCredits( PERSON_ID_DICAPRIO ),
                getExternalIds(     PERSON_ID_DICAPRIO ),
                getImages(          PERSON_ID_DICAPRIO ),
                getTaggedImages(    PERSON_ID_DICAPRIO ),
                getTranslations(    PERSON_ID_DICAPRIO ),
                getLatest(),
                getPopular()
        ) }
    }

    // Reviews.
    @Test fun reviews() {
        tmdbReviews.run { testSinglesStream(
                getDetails( "5488c29bc3a3686f4a00004a" )
        ) }
    }

    // Search.
    @Test fun search() {
        tmdbSearch.run { testSinglesStream(
                multiSearch(        "dicapr" )
                        .map { it.results.joinToString { it.javaClass.simpleName } },
                searchCompanies(    "test" ),
                searchCollections(  "test" ),
                searchLanguages(    "ita" ),
                searchMovies(       "simps"),
                searchPeople(       "dicaprio" ),
                searchTvShows(      "griffin" )
        ) }
    }

    // Tv shows.
    @Test fun tvShows() {
        tmdbTvShows.run { testSinglesStream(
                getDetails              ( TV_SHOW_ID_SIMPSON ),
                getAlternativeTitles    ( TV_SHOW_ID_SIMPSON ),
                getChanges              ( TV_SHOW_ID_SIMPSON ),
                getContentRatings       ( TV_SHOW_ID_SIMPSON ),
                getCredits              ( TV_SHOW_ID_SIMPSON ),
                getExternalIds          ( TV_SHOW_ID_SIMPSON ),
                getImages               ( TV_SHOW_ID_SIMPSON ),
                getKeywords             ( TV_SHOW_ID_SIMPSON ),
                getVideos               ( TV_SHOW_ID_SIMPSON ),
                getTranslations         ( TV_SHOW_ID_SIMPSON ),
                getRecommendations      ( TV_SHOW_ID_SIMPSON ),
                getSimilar              ( TV_SHOW_ID_SIMPSON ),
                getReviews              ( TV_SHOW_ID_SIMPSON ),
                getScreenedTheatrically ( TV_SHOW_ID_SIMPSON ),
                getLatest(),
                getPopular(),
                getTopRated(),
                getAiringToday(),
                getOnTheAir(),
                rateTvShow              ( TV_SHOW_ID_SIMPSON, 6 ),
                removeTvShowRating      ( TV_SHOW_ID_SIMPSON )
        ) }
    }

    // Tv season.
    @Test fun tvSeasons() {
        tmdbTvSeasons.run { testSinglesStream(
                getDetails      ( TV_SHOW_ID_SIMPSON, seasonNumber = 26 ),
                getCredits      ( TV_SHOW_ID_SIMPSON, seasonNumber = 26 ),
                getExternalIds  ( TV_SHOW_ID_SIMPSON, seasonNumber = 26 ),
                getImages       ( TV_SHOW_ID_SIMPSON, seasonNumber = 26 ),
                getVideos       ( TV_SHOW_ID_SIMPSON, seasonNumber = 26 )
        ) }
    }

    //Tv episode.
    @Test fun tvEpisodes() {
        tmdbTvEpisodes.run { testSinglesStream (
                getDetails              ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1 ),
                getCredits              ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1 ),
                getExternalIds          ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1 ),
                getImages               ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1 ),
                getVideos               ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1 ),
                rateTvEpisode           ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1, value =  6 ),
                removeTvEpisodeRating   ( TV_SHOW_ID_SIMPSON, seasonNumber = 26, episodeNumber =  1 )
        ) }
    }

}

private fun testSinglesStream (vararg singles: Single<*>) {
    var last = singles.first().test
    singles.drop(1).forEach { single ->
        last = last.flatMap { single.test }
    }
    last.blockingGet()
}

private val <T> Single<T>.test get() = doOnSuccess {
    val string = if ( it is ResponseBody ) it.string() else it.toString()
    println( "$string\n" )
}