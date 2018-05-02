package studio.forface.rxtmdbapi

import org.junit.Test
import studio.forface.rxtmdbapi.tmdb.TMDB_API_KEY
import studio.forface.rxtmdbapi.tmdb.TmdbApi
import studio.forface.rxtmdbapi.tmdb.models.Extra.*
import studio.forface.rxtmdbapi.tmdb.models.Extras
import java.util.*


private const val SESSION_ID = "<< STORE HERE A SESSION ID >>"
private const val USERNAME = "4face"
private const val PASSWORD = "<< USER PASSWORD >>"

class TmdbApiUnitTest {

    private val tmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, SESSION_ID )
    }

    private val tmdbAuth    get() = tmdbApi.auth
    private val tmdbAccount get() = tmdbApi.account
    private val tmdbConfig  get() = tmdbApi.config
    private val tmdbMovies  get() = tmdbApi.movies
    private val tmdbSearch  get() = tmdbApi.search


    // Auth.
    @Test fun createSession() {
        tmdbAuth.run {
            preloadToken().blockingAwait()

            //val guestSession = createGuessSession().blockingGet()
            val userSession = createUserSessionWithLogin( USERNAME, PASSWORD )
                    .blockingGet()

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

        println( movie.allPeople.joinToString { it.name } )
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

    @Test fun getSomething() {
        val page = tmdbMovies.getNowPlaying()
                .blockingGet()

        println( page.results.joinToString { it.name } )
    }

    // Search.
    @Test fun searchMovie() {
        val page = tmdbSearch.searchMovie(query = "titanic")
                .blockingGet()

        val firstResult = page.results.first()
        println( firstResult )
        println( Date(firstResult.releaseDate).toString() )
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
}