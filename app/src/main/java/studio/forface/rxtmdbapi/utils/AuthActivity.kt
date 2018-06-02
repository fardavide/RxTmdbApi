package studio.forface.rxtmdbapi.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import studio.forface.rxtmdbapi.tmdb.TmdbAuth
import studio.forface.rxtmdbapi.tmdb.TmdbAuthV4
import studio.forface.rxtmdbapi.tmdb.Token

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

private const val TAG = "AuthActivity"
class AuthActivity: Activity() {

    companion object {
        const val PARAM_AUTH_URL = "TmdbAuth.auth_url"
        const val URI = "api://rxtmdb"
        const val URI_V4 = "$URI/v4"
    }

    private val webView by lazy {
        WebView(this ).apply {
            @SuppressLint("SetJavaScriptEnabled")
            settings.javaScriptEnabled = true
        }
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        intent?.getStringExtra( PARAM_AUTH_URL )?.let {
            setContentView( webView )
            webView.loadUrl( it )
        }
    }

    override fun onResume() {
        super.onResume()
        intent?.data?.toString()?.run {

            if ( startsWith( URI_V4 ) ) {
                TmdbAuthV4.tokenAuthorizationSubject
                        .onNext( true )

            } else if ( startsWith( URI ) ) {
                Log.d( TAG, this )
                val approved = substringAfter("&approved=" ).toBoolean()
                if ( approved ) {
                    val token = Token(
                            this
                                    .substringAfter( "token=" )
                                    .substringBefore("&approved=" ),
                            ""
                    )
                    TmdbAuth.tokenAuthorizationSubject
                            .onNext( token )
                } else {
                    TmdbAuth.tokenAuthorizationSubject
                            .onError( Exception( "User didn't authorize the token!" ) )
                }
            }
            finish()
        }
    }

}