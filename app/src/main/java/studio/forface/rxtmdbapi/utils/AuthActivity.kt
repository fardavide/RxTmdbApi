package studio.forface.rxtmdbapi.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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
        AuthWebView(this ) { handleResponse( it ) }
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        intent?.getStringExtra( PARAM_AUTH_URL )?.let {
            setContentView( webView )
            webView.loadUrl( it )
        }
    }

    private fun handleResponse( url: String ) {
        url.run {
            Log.d( TAG, "on response: $this" )

            if ( startsWith( URI_V4 ) ) {
                TmdbAuthV4.tokenAuthorizationSubject
                        .onNext(true )

            } else if ( startsWith( URI ) ) {
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

    override fun onBackPressed() { webView.goBackOr { super.onBackPressed() } }

}

@SuppressLint("ViewConstructor")
private class AuthWebView( context: Context, val onResponse: (String) -> Unit )
    : WebView( context ) {

    internal inline fun goBackOr( block: () -> Unit ) =
            if ( canGoBack() ) goBack()
            else block()

    init {
        @SuppressLint("SetJavaScriptEnabled")
        settings.javaScriptEnabled = true
        webViewClient = Client { onResponse( it ) }
    }

    override fun loadUrl( url: String ) {
        Log.d( TAG, "loading: $url" )
        super.loadUrl(url)
    }

    private class Client( val onResponse: (String) -> Unit ): WebViewClient() {
        override fun shouldOverrideUrlLoading( view: WebView, request: WebResourceRequest ): Boolean {
            val urlString = request.url.toString()
            Log.d( TAG, "overriding: $urlString" )

            return if ( urlString.startsWith( AuthActivity.URI ) ) {
                onResponse( urlString )
                true
            } else false
        }
    }

}