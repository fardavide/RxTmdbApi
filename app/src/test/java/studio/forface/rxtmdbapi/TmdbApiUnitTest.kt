package studio.forface.rxtmdbapi

import org.junit.Assert.assertNotNull
import org.junit.Test
import studio.forface.rxtmdbapi.tmdb.TmdbApi
import studio.forface.rxtmdbapi.tmdb.models.Extra.*
import studio.forface.rxtmdbapi.tmdb.models.Extras
import java.util.*


class TmdbApiUnitTest {

    private val tmdbApi     get() = TmdbApi()
    private val tmdbAuth    get() = tmdbApi.auth
    private val tmdbConfig  get() = tmdbApi.config
    private val tmdbMovies  get() = tmdbApi.movies
    private val tmdbSearch  get() = tmdbApi.search

    // Auth.
    @Test fun getToken() {
        val token = tmdbAuth.getToken()
                .blockingGet()

        assertNotNull(token)
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

    @Test fun getUpcomingMovies() {
        val page = tmdbMovies.getUpcoming()
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