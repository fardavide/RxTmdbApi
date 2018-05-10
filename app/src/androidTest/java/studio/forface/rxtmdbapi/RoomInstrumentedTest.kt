package studio.forface.rxtmdbapi

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import studio.forface.rxtmdbapi.room.LocalDatabase
import studio.forface.rxtmdbapi.room.MoviesDao
import studio.forface.rxtmdbapi.room.updateOrInsertAsync
import studio.forface.rxtmdbapi.tmdb.TmdbApi
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class RoomInstrumentedTest {

    private val tmdbApi = TmdbApi( TMDB_API_KEY, SESSION_ID )
    private lateinit var localDb: LocalDatabase
    private lateinit var moviesDao: MoviesDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        localDb = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java).build()
        moviesDao = localDb.moviesDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        localDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeMovieAndReadInList() {
        val movie = tmdbApi.movies.getDetails(299536 ).blockingGet()

        moviesDao.updateOrInsertAsync(movie)
                .blockingAwait()

        val byId = moviesDao.getMovie(299536 ).blockingGet()

        Log.d( javaClass.simpleName, byId.toString() )
    }

}
