package studio.forface.rxtmdbapi.tmdb

import studio.forface.rxtmdbapi.models.Language

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */
object TmdbApiConfig {
    var includeAdults: Boolean? = null
    var language: Language = defaultLanguage

    private val defaultLanguage
        get() = Language("en" )
}