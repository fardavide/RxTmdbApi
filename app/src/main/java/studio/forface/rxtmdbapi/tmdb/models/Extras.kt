package studio.forface.rxtmdbapi.tmdb.models


enum class Extra {
    ALTERNATIVE_TITLES, CHANGES, CREDITS, EXTERNAL_IDS, IMAGES, KEYWORDS, LISTS,
    RECOMMENDATIONS, RELEASE_DATES, REVIEWS, SIMILAR, TRANSLATIONS, VIDEOS
}


class Extras ( vararg extra: Extra) {

    private val _extras = extra.toList().sortedBy { it.name }

    override fun toString() = _extras.joinToString(separator = ",") { it.name.toLowerCase() }

}



