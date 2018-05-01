package studio.forface.rxtmdbapi.tmdb

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import studio.forface.rxtmdbapi.utils.now
import java.text.SimpleDateFormat
import java.util.*


interface TmdbAuth {
    @GET("authentication/token/new")
    fun getToken(): Single<Token>
}


data class Token(
        @SerializedName("request_token") val value: String,
        @SerializedName("expires_at") private val _expiration: String
) {
    val expiration get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH)
            .parse(_expiration).time
    val expired get() = expiration < now
}