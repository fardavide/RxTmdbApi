package studio.forface.rxtmdbapi

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.util.Log
import org.junit.Test
import studio.forface.rxtmdbapi.tmdb.TmdbApi


/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val TAG = "TmdbInstrumentedTest"

class TmdbInstrumentedTest {

    private val context: Context = InstrumentationRegistry.getTargetContext()
    private val tmdbApi by lazy {
        TmdbApi( TMDB_API_KEY, USER_SESSION_ID )
    }
    private val tmdbAuth            get() = tmdbApi.auth


    @Test fun requestToken() {
        val session = tmdbAuth.createUserSessionWithUserAuthentication( context )
                .subscribe( { Log.d( TAG, it.toString() ) }, {  } )
    }

}