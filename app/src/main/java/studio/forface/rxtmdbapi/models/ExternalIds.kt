package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.utils.EMPTY_STRING


data class ExternalIds (

        @SerializedName(Fields.ID)              @ColumnInfo(name = Fields.ID)
        var id: Int = 0,

        @SerializedName(Fields.IMDB_ID)         @ColumnInfo(name = Fields.IMDB_ID)
        var imdbId: String = EMPTY_STRING,

        @SerializedName(Fields.FACEBOOK_ID)     @ColumnInfo(name = Fields.FACEBOOK_ID)
        var facebookId: String = EMPTY_STRING,

        @SerializedName(Fields.INSTAGRAM_ID)    @ColumnInfo(name = Fields.INSTAGRAM_ID)
        var instagramId: String = EMPTY_STRING,

        @SerializedName(Fields.TWITTER_ID)      @ColumnInfo(name = Fields.TWITTER_ID)
        var twitterId: String = EMPTY_STRING,


        @SerializedName(Fields.TVDB_ID)         @ColumnInfo(name = Fields.TVDB_ID)
        var tvdbId: Int = 0,

        @SerializedName(Fields.FREEBASE_MID)    @ColumnInfo(name = Fields.FREEBASE_MID)
        var freebaseMid: String = EMPTY_STRING,

        @SerializedName(Fields.FREEBASE_ID)     @ColumnInfo(name = Fields.FREEBASE_ID)
        var freebaseId: String = EMPTY_STRING,

        @SerializedName(Fields.TVRAGE_ID)       @ColumnInfo(name = Fields.TVRAGE_ID)
        var tvrageId: Int = 0

)