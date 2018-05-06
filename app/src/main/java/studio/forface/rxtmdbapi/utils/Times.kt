package studio.forface.rxtmdbapi.utils

import java.text.SimpleDateFormat
import java.util.*


val now get() = System.currentTimeMillis()


private const val DATE_LONG_PATTERN = "yyyy-MM-dd HH:mm:ss Z"
private const val DATE_SHORT_PATTERN = "yyyy-MM-dd"

val String?.timeInMillis: Long get() = redundantTry(
        { formatWith(DATE_LONG_PATTERN) },
        { formatWith(DATE_SHORT_PATTERN) },
        { Long.MIN_VALUE }
)

fun String?.formatWith( pattern: String ) =
        SimpleDateFormat( pattern, Locale.ENGLISH ).parse(this).time


data class DateQuery(
        private val year: Int,
        private val month: Int,
        private val day: Int
) {
    override fun toString() = DATE_SHORT_PATTERN
            .replace("yyyy", year.toString())
            .replace("MM", month.toString())
            .replace("dd", day.toString())
}