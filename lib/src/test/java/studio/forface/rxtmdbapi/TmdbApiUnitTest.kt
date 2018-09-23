@file:Suppress("unused")

package studio.forface.rxtmdbapi

import io.reactivex.Single
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import studio.forface.rxtmdbapi.models.Extra.*
import studio.forface.rxtmdbapi.models.Extras
import studio.forface.rxtmdbapi.models.ImageType
import studio.forface.rxtmdbapi.models.MediaType
import studio.forface.rxtmdbapi.models.mapToSizedUrls
import studio.forface.rxtmdbapi.models.requests.ItemRequest
import studio.forface.rxtmdbapi.models.requests.ItemsRequest
import studio.forface.rxtmdbapi.tmdb.*
import studio.forface.rxtmdbapi.utils.Sorting
import java.util.*


private const val ACCOUNT_ID_4FACE = "6574440"
private const val COLLECTION_ID_TRANSFORMERS = 8650
private const val COMPANY_ID_COLUMBIA_PICTURES = 5
private const val GENRE_ID_ANIMATION = 16
private const val GENRE_ID_CRIME = 80
private const val GENRE_ID_DRAMA = 18
private const val LIST_ID_MY_LIST = 64964
private const val MOVIE_ID_BLADE = 335984
private const val NETWORK_ID_RANDOM = "213"
private const val PERSON_ID_DICAPRIO = 6193
private const val TV_SHOW_ID_SIMPSON = 456

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
class TmdbApiUnitTest {

    /*@Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!*/

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()


    private val tmdbApi: TmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, TMDB_API_ACCESS_TOKEN )
                .apply { setSession( USER_SESSION_ID ) }
    }
    private val tmdbApiGuest: TmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, TMDB_API_ACCESS_TOKEN ).apply {
            //setSession( GUEST_SESSION_ID, true )
            onNewGuestSession = { println( "new guest session" ) }
        }
    }
    private val tmdbApiV4: TmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, TMDB_API_ACCESS_TOKEN )
                .apply { setAccessToken( this@TmdbApiUnitTest.tokenV4 ) }
    }

    private val tokenV4 by lazy {
        TokenV4( USER_ACCESS_TOKEN, accountId = USER_ID )
    }

    private val tmdbAuth            get() = tmdbApi.auth
    private val tmdbAuthV4          get() = tmdbApi.authV4
    private val tmdbAccount         get() = tmdbApi.account
    private val tmdbAccountV4       get() = tmdbApiV4.accountV4
    private val tmdbCertifications  get() = tmdbApi.certifications
    private val tmdbChanges         get() = tmdbApi.changes
    private val tmdbCollections     get() = tmdbApi.collections
    private val tmdbCompanies       get() = tmdbApi.companies
    private val tmdbConfig          get() = tmdbApi.config
    private val tmdbDiscover        get() = tmdbApi.discover
    private val tmdbGuest           get() = tmdbApiGuest.guest
    private val tmdbLists           get() = tmdbApi.lists
    private val tmdbListsV4         get() = tmdbApiV4.listsV4
    private val tmdbMovies          get() = tmdbApi.movies
    private val tmdbNetworks        get() = tmdbApi.networks
    private val tmdbPeople          get() = tmdbApi.people
    private val tmdbReviews         get() = tmdbApi.reviews
    private val tmdbSearch          get() = tmdbApi.search
    private val tmdbTvShows         get() = tmdbApi.tvShows
    private val tmdbTvSeasons       get() = tmdbApi.tvSeasons
    private val tmdbTvEpisodes      get() = tmdbApi.tvEpisodes


    // Auth.
    @Test fun auth() {
        tmdbAuth.run {
            preloadToken().blockingAwait()

            val session = createGuessSession().blockingGet()
            //val session = createUserSessionWithLogin( USERNAME, PASSWORD ).blockingGet()

            println( session )
        }
    }

    // Auth v4.
    @Test fun authV4() {
        tmdbAuthV4.run {
            val token = authenticate( tokenV4 )
                    .blockingGet()

            println( token )
        }
    }

    // Account.
    @Test fun account() {
        tmdbAccount.run { testSinglesStream(
                getDetails(),
                getCreatedLists(),
                getFavoriteMovies(),
                getFavoriteTvShows(),
                getRatedMovies(),
                getRatedTvShows(),
                getRatedTvEpisodes(),
                getMoviesWatchlist(),
                getTvShowsWatchlist(),
                addMovieToFavorite(         MOVIE_ID_BLADE ),
                removeMovieFromFavorite(    MOVIE_ID_BLADE ),
                addMovieToWatchlist(        MOVIE_ID_BLADE ),
                removeMovieFromWatchlist(   MOVIE_ID_BLADE )
        ) }
    }

    // Account v4.
    @Test fun accountV4() {
        tmdbAccountV4.run { testSinglesStream(
                getLists(),
                getFavoriteMovies( sortBy = Sorting.ReleaseDate.ASCENDING ),
                getFavoriteTvShows(),
                getRatedMovies(),
                getRatedTvShows(),
                getMoviesWatchlist(),
                getTvShowsWatchlist()
        ) }
    }

    // Certifications.
    @Test fun certifications() {
        tmdbCertifications.run { testSinglesStream(
                getMovieCertifications(),
                getTvCertifications()
        ) }
    }

    // Changes.
    @Test fun changes() {
        tmdbChanges.run { testSinglesStream(
                getMovieChanges(),
                getTvShowChanges(),
                getPersonChanges()
        ) }
    }

    // Collections.
    @Test fun collections() {
        tmdbCollections.run { testSinglesStream(
                getDetails(         COLLECTION_ID_TRANSFORMERS ),
                getImages(          COLLECTION_ID_TRANSFORMERS ),
                getTranslations(    COLLECTION_ID_TRANSFORMERS )
        ) }
    }

    // Companies.
    @Test fun companies() {
        tmdbCompanies.run { testSinglesStream(
                getDetails(             COMPANY_ID_COLUMBIA_PICTURES ),
                getAlternativeNames(    COMPANY_ID_COLUMBIA_PICTURES),
                getImages(              COMPANY_ID_COLUMBIA_PICTURES)
        ) }
    }

    // Config.
    @Test fun config() {
        tmdbConfig.run { testSinglesStream(
                getApiConfig(),
                getImagesConfig(),
                getCountries(),
                getMovieGenres(),
                getTvShowGenres(),
                getLanguages()
        ) }
    }

    // Discover.
    @Test fun discover() {
        tmdbDiscover.run { testSinglesStream(
                movieDiscover(
                        includeAdults = true,
                        year = 2017,
                        sortBy = Sorting.VoteCount.ASCENDING
                ).map { it.resultsCount },
                movieDiscover(
                        withGenres = listOf(
                                GENRE_ID_ANIMATION,
                                GENRE_ID_CRIME
                        )
                ).map { it.resultsCount },
                tvShowDiscover(
                        // no params.
                ).map { it.resultsCount }
        ) }
    }

    // Guest.
    @Test fun guest() {
        tmdbGuest.run { testSinglesStream(
                getRatedMovies(),
                getRatedTvShows(),
                getRatedTvEpisodes()
        ) }
    }

    // Lists V4.
    @Test fun listsV4() {
        tmdbListsV4.run { testSinglesStream(
                getDetails( LIST_ID_MY_LIST ),
                clearList( LIST_ID_MY_LIST ),
                //createList("API test list","desc" ), // TESTED, do not run too many times.
                updateList( LIST_ID_MY_LIST, "name ${Random().nextInt()}" )
                //deleteList( 80229 )
                // deleteList(80192 ), // TESTED, do not run too many times.
                // addItems(), // TESTED, do not run too many times.
        ) }
    }

    @Test fun listsV4AddItems() {
        val response = tmdbListsV4.addItems().blockingGet()
        println( response.string() )
    }

    private fun TmdbListsV4.addItems() = addItems( LIST_ID_MY_LIST, ItemsRequest(
            MediaType.MOVIE to MOVIE_ID_BLADE ,
            MediaType.TV_SHOW to TV_SHOW_ID_SIMPSON
    ) )

    @Test fun listsV4RemoveItems() {
        val response = tmdbListsV4.removeItems().blockingGet()
        println( response.string() )
    }

    private fun TmdbListsV4.removeItems() = removeItems( LIST_ID_MY_LIST, ItemsRequest(
            MediaType.MOVIE to MOVIE_ID_BLADE ,
            MediaType.TV_SHOW to TV_SHOW_ID_SIMPSON
    ) )

    @Test fun listsV4CommentItems() {
        val response = tmdbListsV4.commentItems().blockingGet()
        println( response.string() )
    }

    private fun TmdbListsV4.commentItems() = commentItems( LIST_ID_MY_LIST, ItemsRequest(
            ItemRequest( MediaType.MOVIE, MOVIE_ID_BLADE,"awesome!!"),
            ItemRequest( MediaType.TV_SHOW, TV_SHOW_ID_SIMPSON,"wooow" )
    ) )

    @Test fun listsV4RemoveComments() {
        val response = tmdbListsV4.removeComments().blockingGet()
        println( response.string() )
    }

    private fun TmdbListsV4.removeComments() = removeComments( LIST_ID_MY_LIST, ItemsRequest(
            MediaType.MOVIE to MOVIE_ID_BLADE,
            MediaType.TV_SHOW to TV_SHOW_ID_SIMPSON
    ) )

    /*@Test fun listsV4CheckItemStatus() {
        val response = tmdbListsV4.checkItemStatus().blockingGet()
        println( response.string() )
    }

    private fun TmdbListsV4.checkItemStatus() = checkItemStatus(
            LIST_ID_MY_LIST, MediaType.TV_SHOW, TV_SHOW_ID_SIMPSON
    )*/

    @Test fun listsV4HasItem() {
        val response = tmdbListsV4.hasItem().blockingGet()
        println( response )
    }

    private fun TmdbListsV4.hasItem() = hasItem(
            LIST_ID_MY_LIST, MediaType.TV_SHOW, TV_SHOW_ID_SIMPSON
    )

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
                getLists(               MOVIE_ID_BLADE ),
                getLatest(),
                getNowPlaying(),
                getPopular(),
                getTopRated(),
                getUpcoming(),
                //rateMovie(          MOVIE_ID_BLADE, 6 ),
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

    // Tv episode.
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

    // Images.
    @Test fun images() {
        val imagesConfig = tmdbConfig.getImagesConfig().blockingGet().apply {
            sizeFinderTolerancePercentage = 50
        }

        val images = tmdbMovies.getDetails( MOVIE_ID_BLADE, extras = Extras(IMAGES) )
                .map { it.backdrops }
                .map { it.sortedByDescending { it.voteAverage } }
                .map {
                    it.mapToSizedUrls(
                            imagesConfig,
                            ImageType.BACKDROP,
                            1280,
                            true
                ) }.blockingGet()

        println( images )
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
    val string = if (it is ResponseBody) it.string() else it.toString()
    println("$string\n")
}.retry( Long.MAX_VALUE ) { t -> ( t as? HttpException )?.code() == 429 }