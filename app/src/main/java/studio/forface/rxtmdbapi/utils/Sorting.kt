@file:Suppress("unused")

package studio.forface.rxtmdbapi.utils


sealed class Sorting( private val toString: String ) {

    interface MovieSorting
    interface TvShowSorting

    sealed class CreationDate {
        object ASCENDING:   Sorting( "created_at.asc" )
        object DESCENDING:  Sorting( "created_at.desc" )
    }

    sealed class FirstAirDate:                      TvShowSorting {
        object ASCENDING:   Sorting( "first_air_date.asc" )
        object DESCENDING:  Sorting( "first_air_date.desc" )
    }

    sealed class OriginalTitle:     MovieSorting {
        object ASCENDING:   Sorting( "original_title.asc" )
        object DESCENDING:  Sorting( "original_title.desc" )
    }

    sealed class Popularity:        MovieSorting,   TvShowSorting {
        object ASCENDING:   Sorting( "popularity.asc" )
        object DESCENDING:  Sorting( "popularity.desc" )
    }

    sealed class PrimaryRelease:    MovieSorting {
        object ASCENDING:   Sorting( "primary_release.asc" )
        object DESCENDING:  Sorting( "primary_release.desc" )
    }

    sealed class ReleaseDate:       MovieSorting {
        object ASCENDING:   Sorting( "release_date.asc" )
        object DESCENDING:  Sorting( "release_date.desc" )
    }

    sealed class Revenue:           MovieSorting {
        object ASCENDING:   Sorting( "revenue.asc" )
        object DESCENDING:  Sorting( "revenue.desc" )
    }

    sealed class VoteAverage:       MovieSorting,   TvShowSorting {
        object ASCENDING:   Sorting( "vote_average.asc" )
        object DESCENDING:  Sorting( "vote_average.desc" )
    }

    sealed class VoteCount:         MovieSorting {
        object ASCENDING:   Sorting( "vote_count.asc" )
        object DESCENDING:  Sorting( "vote_count.desc" )
    }

    override fun toString() = toString
}