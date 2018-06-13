package studio.forface.rxtmdbapi.utils


const val EMPTY_STRING = ""

fun String.toIntOrZero() = toIntOrNull() ?: 0

/**
 * @see startsWith
 * @return `false` if this char sequence starts with the specified character.
 */
fun CharSequence.notStartsWith( char: Char, ignoreCase: Boolean = false) : Boolean =
        ! startsWith( char, ignoreCase )

/**
 * @see startsWith
 * @return `false` if this char sequence starts with the specified prefix.
 */
fun CharSequence.notStartsWith( prefix: CharSequence, ignoreCase: Boolean = false ): Boolean =
        ! startsWith( prefix, ignoreCase )

/**
 * @see startsWith
 * @return `false` if a substring of this char sequence starting at the specified offset
 * [startIndex] starts with the specified prefix.
 */
fun CharSequence.notStartsWith(
        prefix: CharSequence,
        startIndex: Int,
        ignoreCase: Boolean = false
): Boolean = startsWith( prefix, startIndex, ignoreCase )