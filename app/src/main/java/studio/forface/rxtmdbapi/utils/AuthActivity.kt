package studio.forface.rxtmdbapi.utils

import android.app.Activity
import android.util.Log

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val TAG = "AuthActivity"
class AuthActivity: Activity() {

    override fun onResume() {
        super.onResume()

        intent?.data?.toString()?.run {
            if ( startsWith( "api://rxtmdb" ) ) {
                Log.i( TAG, this )
            }
        }

    }

}