package studio.forface.annotations.utils

import retrofit2.http.*
import javax.lang.model.element.Element


internal val retrofitAnnotations = setOf(
        Body::class,
        DELETE::class,
        Field::class,
        FieldMap::class,
        FormUrlEncoded::class,
        GET::class,
        HEAD::class,
        Header::class,
        HeaderMap::class,
        Headers::class,
        HTTP::class,
        Multipart::class,
        OPTIONS::class,
        Part::class,
        PartMap::class,
        Path::class,
        PATCH::class,
        POST::class,
        PUT::class,
        Query::class,
        QueryMap::class,
        QueryName::class,
        Streaming::class,
        Url::class
)

internal val retrofitAnnotationsName get() = retrofitAnnotations.map { it.simpleName }

/**
 * Get all the Retrofit [Annotation]'s of an [Element].
 */
internal fun Element.retrofitAnnotations() =
    retrofitAnnotations.flatMap { this.getAnnotationsByType( it.java ).asList() }