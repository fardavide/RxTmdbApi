package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.timeInMillis


@Entity(tableName = Session.TABLE_NAME)
open class Session constructor (

        @ColumnInfo(name = Fields.SESSION_ID)
        open var sessionId: String,

        @ColumnInfo(name = Fields.GUEST)
        var guest: Boolean,

        @PrimaryKey
        var id: Int = 0

) {

    companion object {
        internal const val TABLE_NAME = "sessions"
    }

    @Ignore @Transient
    open val success: Boolean = true

    override fun toString() = sessionId


    data class User internal constructor(

        @SerializedName(Fields.SUCCESS) override            val success: Boolean,
        @SerializedName(Fields.SESSION_ID) override         var sessionId: String

    ) : Session( sessionId, false )


    data class Guest internal constructor(

        @SerializedName(Fields.SUCCESS) override            val success: Boolean,
        @SerializedName(Fields.GUEST_SESSION_ID) override   var sessionId: String,
        @SerializedName(Fields.EXPIRES_AT) private          val _expiration: String

    ) : Session( sessionId, true ) {

        val expiration get() = _expiration.timeInMillis

    }

}