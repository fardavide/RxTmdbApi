@file:Suppress("unused")

package studio.forface.rxtmdbapi.utils

/**
 * a const for reference to an empty [String].
 */
const val EMPTY_STRING = ""

/**
 * A simple infix function for check [String.equals] ignoring the case, just writing:
 * string1 equalsNoCase string2
 * @return a [Boolean].
 */
infix fun String.equalsNoCase( other: String? ) = this.equals( other, true )

/**
 * @see [String.toIntOrNull].
 * @return the [Int] value or "0" instead on 'null'.
 */
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