package studio.forface.rxtmdbapi

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.v4.content.ContextCompat.startActivity
import org.junit.Test


/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val BASE_URL = "https://api.themoviedb.org/4/auth/request_token="

private const val TAG = "TmdbInstrumentedTest"

class TmdbInstrumentedTest {

    private val context: Context = InstrumentationRegistry.getTargetContext()

    @Test fun requestToken() {

        val uri = Uri.parse( BASE_URL )

        val intent = Intent( Intent.ACTION_VIEW, uri )
        context.startActivity( intent )

    }

}