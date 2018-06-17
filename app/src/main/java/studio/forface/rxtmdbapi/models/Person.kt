package studio.forface.rxtmdbapi.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import studio.forface.rxtmdbapi.models.Person.Companion.TABLE_NAME
import studio.forface.rxtmdbapi.utils.EMPTY_STRING
import studio.forface.rxtmdbapi.utils.ModelTypeConverters
import studio.forface.rxtmdbapi.utils.timeInMillis

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Entity(tableName = TABLE_NAME)
@TypeConverters( ModelTypeConverters::class )
data class Person(

    @SerializedName(Fields.ID)                  @ColumnInfo(name = Fields.ID)
    @PrimaryKey
    override var id: Int = 0,

    @SerializedName(Fields.ADULT)               @ColumnInfo(name = Fields.ADULT)
    var adult: Boolean = false,

    @SerializedName(Fields.ALSO_KNOWN_AS)       @ColumnInfo(name = Fields.ALSO_KNOWN_AS)
    var alsoKnownAs: List<String> = listOf(),

    @SerializedName(Fields.BIOGRAPHY)           @ColumnInfo(name = Fields.BIOGRAPHY)
    var biography: String = EMPTY_STRING,

    @SerializedName(Fields.BIRTHDAY)            @ColumnInfo(name = Fields.BIRTHDAY)
    var _birthday: String = EMPTY_STRING,

    @SerializedName(Fields.DEATHDAY)            @ColumnInfo(name = Fields.DEATHDAY)
    var _deathday: String = EMPTY_STRING,

    @SerializedName(Fields.GENDER)              @ColumnInfo(name = Fields.GENDER)
    var _gender: Int = 0,

    @SerializedName(Fields.HOMEPAGE)            @ColumnInfo(name = Fields.HOMEPAGE)
    var homepage: String = EMPTY_STRING,

    @SerializedName(Fields.IMDB_ID)             @ColumnInfo(name = Fields.IMDB_ID)
    var imdbId: String? = EMPTY_STRING,

    @SerializedName(Fields.NAME)                @ColumnInfo(name = Fields.NAME)
    override var name: String = EMPTY_STRING,

    @SerializedName(Fields.PLACE_OF_BIRTH)      @ColumnInfo(name = Fields.PLACE_OF_BIRTH)
    var placeOfBirth: String = EMPTY_STRING,

    @SerializedName(Fields.POPULARITY)          @ColumnInfo(name = Fields.POPULARITY)
    var popularity: Double = 0.0,

    @SerializedName(Fields.PROFILE_PATH)        @ColumnInfo(name = Fields.PROFILE_PATH)
    var profilePath: String = EMPTY_STRING

) : Media( MediaType.PERSON.name ), NamedIdElement, Pageable {

    companion object {
        internal const val TABLE_NAME = "people"
    }

    val birthday get() = _birthday.timeInMillis
    val deathday get() = _deathday.timeInMillis
    val gender   get() = Gender.fromValue( _gender )

}

enum class Gender (val value: Int) {
    NOT_SET (0),
    MALE    (1),
    FEMALE  (2);

    companion object {
        val fromValue: (Int) -> Gender get() = { value ->
            values().find { it.value == value } ?: NOT_SET
        }
    }
}
