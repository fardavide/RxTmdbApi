package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.Company.Companion.TABLE_NAME
import studio.forface.rxtmdbapi.utils.EMPTY_STRING

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Entity(tableName = TABLE_NAME)
data class Company(

        @SerializedName(Fields.ID)              @ColumnInfo(name = Fields.ID)
        @PrimaryKey
        override var id: Int = 0,

        @SerializedName(Fields.DESCRIPTION)     @ColumnInfo(name = Fields.DESCRIPTION)
        var description: String = EMPTY_STRING,

        @SerializedName(Fields.HEADQUARTERS)    @ColumnInfo(name = Fields.HEADQUARTERS)
        var headquarters: String = EMPTY_STRING,

        @SerializedName(Fields.HOMEPAGE)        @ColumnInfo(name = Fields.HOMEPAGE)
        var homepage: String = EMPTY_STRING,

        @SerializedName(Fields.LOGO_PATH)       @ColumnInfo(name = Fields.LOGO_PATH)
        var logoPath: String = EMPTY_STRING,

        @SerializedName(Fields.NAME)            @ColumnInfo(name = Fields.NAME)
        override var name: String = EMPTY_STRING,

        @SerializedName(Fields.ORIGIN_COUNTRY)  @ColumnInfo(name = Fields.ORIGIN_COUNTRY)
        var originCountry: String = EMPTY_STRING

        /*@SerializedName(Fields.PARENT_COMPANY)  @Embedded(prefix = Fields.PARENT_COMPANY)
        var parentCompany: Company?*/


) : NamedIdElement, Pageable {

    companion object {
        internal const val TABLE_NAME = "companies"
    }

}