package studio.forface.rxtmdbapi

import org.junit.Test
import studio.forface.rxtmdbapi.utils.timeInMillis
import java.util.*


class TimesUnitTest {

    @Test fun fullDateToTimeInMillis() {
        val input = "2000-10-21 20:30:00 UTC"
        val output = input.timeInMillis.toCalendar()

        print(input, output)
    }

    @Test fun shortDateToTimeInMillis() {
        val input = "2000-10-21"
        val output = input.timeInMillis.toCalendar()

        print(input, output)
    }

    @Test fun releaseDateToTimeInMillis() {
        print( "", mockedReleaseDate.date.toCalendar() )
    }


    private fun print(input: String, output: Calendar) {
        println()
        println("input: $input")
        println("output: ${output.string}")
    }


    private fun Long.toCalendar()
        = Calendar.getInstance().apply { timeInMillis = this@toCalendar }

    private val Calendar.string: String
        get() = "" + get(Calendar.DAY_OF_MONTH) +
                "-" + ( get(Calendar.MONTH) + 1 ) +
                "-" + get(Calendar.YEAR) +
                " " + get(Calendar.HOUR_OF_DAY) +
                ":" + get(Calendar.MINUTE)

}