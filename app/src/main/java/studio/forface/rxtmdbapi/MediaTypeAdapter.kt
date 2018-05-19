package studio.forface.rxtmdbapi

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

        val mediaType = json.asJsonObject
                .get( Fields.MEDIA_TYPE )
                .toString()
                .trim( '"' )
                .toUpperCase()

        val clazz = when ( mediaType ) {
            MediaType.MOVIE.name ->     Movie::class.java
            MediaType.TV.name ->        TvShow::class.java
            MediaType.PERSON.name ->    Person::class.java
            else -> throw IllegalArgumentException()
        }

        return Gson().fromJson( json, clazz ) as Media
    }
}