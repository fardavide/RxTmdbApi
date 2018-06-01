package studio.forface.rxtmdbapi.utils

import android.app.Activity
import android.util.Log
import studio.forface.rxtmdbapi.tmdb.TmdbAuth
import studio.forface.rxtmdbapi.tmdb.Token

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val TAG = "AuthActivity"
class AuthActivity: Activity() {

    companion object {
        const val URI = "api://rxtmdb"
    }

    override fun onResume() {
        super.onResume()

        intent?.data?.toString()?.run {
            if ( startsWith( URI ) ) {
                val approved = this.substringAfter( "&approved=" ).toBoolean()
                if ( approved ) {
                    val token = Token(
                            this
                                    .substringAfter("token=")
                                    .substringBefore("&approved="),
                            ""
                    )
                    TmdbAuth.Companion.tokenAuthorizationSubject
                            .onNext( token )
                    Log.d( TAG, token.toString() )
                } else {
                    TmdbAuth.Companion.tokenAuthorizationSubject
                            .onError( Exception( "User didn't authorize the token!" ) )
                }
                //finish()
            }
        }

    }

}