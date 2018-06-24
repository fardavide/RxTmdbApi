@file:Suppress("unused")

package studio.forface.rxtmdbapi.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import studio.forface.rxtmdbapi.models.*

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

class ModelTypeConverters {

    val gson = Gson()

    @TypeConverter fun String?.alternativeTitles():                     List<AlternativeTitles> = toList()
    @TypeConverter fun List<AlternativeName>?.alternativeTitles():     String = toJsonString()

    @TypeConverter fun String?.changesList():                           List<Changes> = toList()
    @TypeConverter fun List<Changes>?.changesList():                    String = toJsonString()

    @TypeConverter fun String?.castList():                              List<Cast> = toList()
    @TypeConverter fun List<Cast>?.castList():                          String = toJsonString()

    @TypeConverter fun String?.companyList():                           List<Company> = toList()
    @TypeConverter fun List<Company>?.companyList():                    String = toJsonString()

    @TypeConverter fun String?.countryList():                           List<Country> = toList()
    @TypeConverter fun List<Country>?.countryList():                    String = toJsonString()

    @TypeConverter fun String?.crewList():                              List<Crew> = toList()
    @TypeConverter fun List<Crew>?.crewList():                          String = toJsonString()

    @TypeConverter fun String?.genreList():                             List<Genre> = toList()
    @TypeConverter fun List<Genre>?.genreList():                        String = toJsonString()

    @TypeConverter fun String?.imageList():                             List<Image> = toList()
    @TypeConverter fun List<Image>?.imageList():                        String = toJsonString()

    @TypeConverter fun String?.integerList():                           List<Int> = toList()
    @TypeConverter fun List<Int>?.integerList():                        String = toJsonString()

    @TypeConverter fun String?.keywordList():                           List<Keyword> = toList()
    @TypeConverter fun List<Keyword>?.keywordList():                    String = toJsonString()

    @TypeConverter fun String?.languageList():                          List<Language> = toList()
    @TypeConverter fun List<Language>?.languageList():                  String = toJsonString()

    @TypeConverter fun String?.networkList():                           List<Network> = toList()
    @TypeConverter fun List<Network>?.networkList():                    String = toJsonString()

    @TypeConverter fun String?.partList():                              List<Part> = toList()
    @TypeConverter fun List<Part>?.partList():                          String = toJsonString()

    @TypeConverter fun String?.personList():                            List<Person> = toList()
    @TypeConverter fun List<Person>?.personList():                      String = toJsonString()

    @TypeConverter fun String?.regionReleases():                        List<RegionRelease> = toList()
    @TypeConverter fun List<RegionRelease>?.regionReleases():           String = toJsonString()

    @TypeConverter fun String?.seasonList():                            List<Season> = toList()
    @TypeConverter fun List<Season>?.seasonList():                      String = toJsonString()

    @TypeConverter fun String?.stringList():                            List<String> = toList()
    @TypeConverter fun List<String>?.stringList():                      String = toJsonString()

    @TypeConverter fun String?.movieTranslations():                     List<Translation<Movie>> = toList()
    @TypeConverter fun List<Translation<Movie>>?.movieTranslations():   String = toJsonString()

    @TypeConverter fun String?.videoList():                             List<Video> = toList()
    @TypeConverter fun List<Video>?.videoList():                        String = toJsonString()


    // Deserialize.
    private fun <T> String?.toList(): List<T> =
            this?.let {
                gson.fromJson<List<T>>(it, object : TypeToken<List<T>>() {}.type)
            } ?: listOf()

    // Serialize.
    private fun <T> T?.toJsonString(): String = gson.toJson( this )

}