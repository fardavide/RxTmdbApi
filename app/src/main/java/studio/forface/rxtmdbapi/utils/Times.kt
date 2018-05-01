package studio.forface.rxtmdbapi.utils

import java.text.SimpleDateFormat
import java.util.*


val now get() = System.currentTimeMillis()


val String?.timeInMillis: Long get() = redundantTry(
        { formatWith("yyyy-MM-dd HH:mm:ss Z") },
        { formatWith("yyyy-MM-dd") },
        { Long.MIN_VALUE }
)

fun String?.formatWith( pattern: String ) =
        SimpleDateFormat( pattern, Locale.ENGLISH ).parse(this).time
