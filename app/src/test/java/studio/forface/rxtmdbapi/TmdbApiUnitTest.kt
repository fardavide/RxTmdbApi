package studio.forface.rxtmdbapi

import org.junit.Test
import studio.forface.rxtmdbapi.models.Extra.*
import studio.forface.rxtmdbapi.models.Extras
import studio.forface.rxtmdbapi.tmdb.TmdbApi
import studio.forface.rxtmdbapi.tmdb.removeMovieFromFavorite
import studio.forface.rxtmdbapi.tmdb.removeMovieFromWatchlist
import studio.forface.rxtmdbapi.utils.DateQuery
import studio.forface.rxtmdbapi.utils.Sorting


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
    private val tmdbSearch          get() = tmdbApi.search
    private val tmdbTvShows         get() = tmdbApi.tvShows


    // Auth.
    @Test fun createSession() {
        tmdbAuth.run {
            preloadToken().blockingAwait()

            val guestSession = createGuessSession().blockingGet()
            //val userSession = createUserSessionWithLogin( USERNAME, PASSWORD ).blockingGet()

            println( guestSession )
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
    @Test fun getMovieDetails() {
        val movie = tmdbMovies.getDetails( id = 335984, extras = Extras( VIDEOS, IMAGES, KEYWORDS, REVIEWS, CREDITS ))
                .blockingGet()

        println( movie.belongsToCollection )
    }

    @Test fun getLatestMovie() {
        val movie = tmdbMovies.getLatest()
                .blockingGet()

        println( movie )
    }

    @Test fun getPopularMovies() {
        val page = tmdbMovies.getPopular( page = 3, language = "it", region = "IT" )
                .blockingGet()

        println( page.results.joinToString { it.name } )
    }

    @Test fun getTopRatedMovies() {
        val page = tmdbMovies.getTopRated()
                .blockingGet()

        println( page.results.joinToString { it.name } )
    }

    @Test fun getUpcomingMovies() {
        val page = tmdbMovies.getUpcoming()
                .blockingGet()

        println( page.results.joinToString { it.name } )
    }

    @Test fun getMovieSomething() {
        val item = tmdbMovies.getRecommendations(335984)
                .blockingGet()

        println( item )
    }

    @Test fun rateMovie() {
        tmdbAuth.createGuessSession().blockingGet()
        val response = tmdbMovies.rateMovie(181808, 8.5)
                .blockingGet()

        println( response.string() )
    }

    // Search.
    @Test fun searchMovie() {
        val page = tmdbSearch.searchMovie(query = "titanic")
                .blockingGet()

        val firstResult = page.results.first()
        println( firstResult )
        //println( Date(firstResult.releaseDate).toString() )
    }

    @Test fun searchPeople() {
        val page = tmdbSearch.searchPeople(query = "muffy")
                .blockingGet()

        val firstResult = page.results.first()
        println( firstResult )
    }

    @Test fun searchTv() {
        val page = tmdbSearch.searchTv(query = "griffin")
                .blockingGet()

        println(page.toString())
    }

    // Tv.
    @Test fun getTvSomething() {
        val result = tmdbTvShows.getOnTheAir()
                .blockingGet()

        println( result )
    }
}