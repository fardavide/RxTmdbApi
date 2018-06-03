package studio.forface.rxtmdbapi

import org.junit.Test
import studio.forface.rxtmdbapi.models.ReleaseDate


val mockedReleaseDate get() = ReleaseDate (
        certification = "",
        iso6391 = "",
        note = null,
        _date = "2000-03-01T00:00:00.000Z",
        _type = 3
)

class MiscUnitTest {

    @Test fun queryList() {
        println( listOf( 1, 2, 3, 4 ) )
    }

    @Test fun releaseDateReleaseType() {
        println( mockedReleaseDate.type )
    }

}