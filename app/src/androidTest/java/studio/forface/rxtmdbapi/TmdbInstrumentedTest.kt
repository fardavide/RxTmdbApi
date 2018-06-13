package studio.forface.rxtmdbapi

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.util.Log
import org.junit.Test
import studio.forface.rxtmdbapi.tmdb.TmdbApi
import studio.forface.rxtmdbapi.utils.Sorting


/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val TAG = "TmdbInstrumentedTest"

class TmdbInstrumentedTest {

    private val context: Context = InstrumentationRegistry.getTargetContext()
    private val tmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, TMDB_API_ACCESS_TOKEN, USER_SESSION_ID )
    }
    private val tmdbAuth            get() = tmdbApi.auth
    private val tmdbAuthV4          get() = tmdbApi.authV4

    @Test fun auth() {
        val session = tmdbAuth.createUserSessionWithUserAuthentication( context )
                .blockingGet()

        Log.d( TAG, session.toString() )
    }

    @Test fun authV4() {
        tmdbAuthV4.preloadToken().blockingAwait()
        tmdbAuthV4.createAccessToken( context )
                .blockingGet()

        val result = tmdbApi.accountV4.getFavoriteMovies(
                sortBy = Sorting.CreationDate.ASCENDING
        ).blockingGet()

        Log.d( TAG, result.toString() )
    }

}