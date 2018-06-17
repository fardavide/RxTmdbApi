package studio.forface.rxtmdbapi.utils

import com.google.gson.*
import studio.forface.rxtmdbapi.models.*
import java.lang.reflect.Type

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

internal class MediaSerializer : JsonSerializer<Media> {

    override fun serialize(
            src: Media,
            typeOfSrc: Type,
            context: JsonSerializationContext
    ): JsonElement {

        return Gson().toJsonTree( src, typeOfSrc )
    }
}

internal class MediaDeserializer : JsonDeserializer<Media> {

    @Throws(JsonParseException::class)
    override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
    ): Media {

        val mediaTypeString = json.asJsonObject
                .get( Fields.MEDIA_TYPE )
                .toString()
                .trim( '"' )
                .toUpperCase()

        val mediaType = MediaType[mediaTypeString]

        val clazz = when ( mediaType ) {
            MediaType.MOVIE ->   Movie::class.java
            MediaType.TV_SHOW -> TvShow::class.java
            MediaType.PERSON ->  Person::class.java
            else -> throw IllegalArgumentException()
        }

        return Gson().fromJson( json, clazz ) as Media
    }
}