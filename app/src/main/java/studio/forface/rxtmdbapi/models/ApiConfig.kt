@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.ImageType.*
import studio.forface.rxtmdbapi.models.ImagesConfig.Companion.TABLE_NAME
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.toIntOrZero

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

data class ApiConfig (

    @SerializedName(Fields.IMAGES)           val imagesConfig: ImagesConfig,
    @SerializedName(Fields.CHANGE_KEYS)      val changeKeys: List<String>

)

@Entity(tableName = TABLE_NAME)
data class ImagesConfig (

    @SerializedName(Fields.BASE_URL)            @ColumnInfo(name = Fields.BASE_URL)
    var baseUrl: String = EMPTY_STRING,

    @SerializedName(Fields.SECURE_BASE_URL)     @ColumnInfo(name = Fields.SECURE_BASE_URL)
    var secureBaseUrl: String = EMPTY_STRING,

    @SerializedName(Fields.BACKDROP_SIZES)      @ColumnInfo(name = Fields.BACKDROP_SIZES)
    var backdropSizes: List<String> = listOf(),

    @SerializedName(Fields.LOGO_SIZES)          @ColumnInfo(name = Fields.LOGO_SIZES)
    var logoSizes: List<String> = listOf(),

    @SerializedName(Fields.POSTER_SIZES)        @ColumnInfo(name = Fields.POSTER_SIZES)
    var posterSizes: List<String> = listOf(),

    @SerializedName(Fields.PROFILE_SIZES)       @ColumnInfo(name = Fields.PROFILE_SIZES)
    var profileSizes: List<String> = listOf(),

    @SerializedName(Fields.STILL_SIZES)         @ColumnInfo(name = Fields.STILL_SIZES)
    var stillSizes: List<String> = listOf(),

    @PrimaryKey var id: Int = 0

) {

    companion object {
        internal const val TABLE_NAME = "images_config"
    }

    /**
     * This value is used for avoid to load to big Images.
     * For instance: if on the available widths we have 1280, our View's width is 1300px and we have
     * properly set a tolerance percentage, the 1280's Image will be loaded, instead of the original
     * one which may be 3 times larger.
     */
    var sizeFinderTolerancePercentage = 10
        set(value) { field = value.coerceAtLeast(0).coerceAtMost(100) }

    fun bestSizeUrl(
            imageType: ImageType,
            requestedWidth: Int,
            imageFilePath: String = EMPTY_STRING,
            secureConnection: Boolean = false
    ) = with( when( imageType ) {
        BACKDROP -> backdropSizes
        LOGO -> logoSizes
        POSTER -> posterSizes
        PROFILE -> profileSizes
        STILL -> stillSizes
    } ) { buildUrl( this, requestedWidth, imageFilePath, secureConnection ) }

    fun bestBackdropSizeUrl(
            requestedWidth: Int,
            imageFilePath: String = EMPTY_STRING,
            secureConnection: Boolean = false
    ) = buildUrl( backdropSizes, requestedWidth, imageFilePath, secureConnection )

    fun bestLogoSizeUrl(
            requestedWidth: Int,
            imageFilePath: String = EMPTY_STRING,
            secureConnection: Boolean = false
    ) = buildUrl( logoSizes, requestedWidth, imageFilePath, secureConnection )

    fun bestPosterSizeUrl(
            requestedWidth: Int,
            imageFilePath: String = EMPTY_STRING,
            secureConnection: Boolean = false
    ) = buildUrl( posterSizes, requestedWidth, imageFilePath, secureConnection )

    fun bestProfileSizeUrl(
            requestedWidth: Int,
            imageFilePath: String = EMPTY_STRING,
            secureConnection: Boolean = false
    ) = buildUrl( profileSizes, requestedWidth, imageFilePath, secureConnection )

    fun bestStillSizeUrl(
            requestedWidth: Int,
            imageFilePath: String = EMPTY_STRING,
            secureConnection: Boolean = false
    ) = buildUrl( stillSizes, requestedWidth, imageFilePath, secureConnection )

    private fun buildUrl(
            list: List<String>,
            requestedWidth: Int,
            imageFilePath: String,
            secureConnection: Boolean
    ) = ( if (secureConnection) secureBaseUrl else baseUrl ).let { baseUrl ->
        baseUrl + sizeFinder(list, requestedWidth) + imageFilePath
    }

    private val sizeFinder: ( List<String>, Int ) -> String get() = { list, requestedWidth ->
        val tolerance = requestedWidth * sizeFinderTolerancePercentage / 100
        list.firstOrNull {
            it.trim('w').toIntOrZero() >= ( requestedWidth - tolerance )
        } ?: list.last()
    }

}