@file:Suppress("unused")

package studio.forface.rxtmdbapi.utils


sealed class Sorting( private val toString: String ) {

    interface MovieSorting
    interface TvShowSorting

    interface ListSorting

    interface ListMovieSorting
    interface ListTvShowSorting

    sealed class CreationDate( toString: String ): Sorting( toString ),
            ListMovieSorting,   ListTvShowSorting {
        object ASCENDING:   CreationDate( "created_at.asc" )
        object DESCENDING:  CreationDate( "created_at.desc" )
    }

    sealed class FirstAirDate( toString: String): Sorting( toString ),
                            TvShowSorting {
        object ASCENDING:   FirstAirDate( "first_air_date.asc" )
        object DESCENDING:  FirstAirDate( "first_air_date.desc" )
    }

    sealed class OriginalTitle( toString: String): Sorting( toString ),
            MovieSorting {
        object ASCENDING:   OriginalTitle( "original_title.asc" )
        object DESCENDING:  OriginalTitle( "original_title.desc" )
    }

    sealed class Popularity( toString: String): Sorting( toString ),
            MovieSorting,       TvShowSorting {
        object ASCENDING:   Popularity( "popularity.asc" )
        object DESCENDING:  Popularity( "popularity.desc" )
    }

    sealed class PrimaryRelease( toString: String): Sorting( toString ),
            MovieSorting {
        object ASCENDING:   PrimaryRelease( "primary_release.asc" )
        object DESCENDING:  PrimaryRelease( "primary_release.desc" )
    }

    sealed class ReleaseDate( toString: String): Sorting( toString ),
            ListSorting,
            MovieSorting,
            ListMovieSorting {
        object ASCENDING:   ReleaseDate( "release_date.asc" )
        object DESCENDING:  ReleaseDate( "release_date.desc" )
    }

    sealed class Revenue( toString: String): Sorting( toString ),
            MovieSorting {
        object ASCENDING:   Revenue( "revenue.asc" )
        object DESCENDING:  Revenue( "revenue.desc" )
    }

    sealed class Title( toString: String): Sorting( toString ),
            ListSorting,
            ListMovieSorting {
        object ASCENDING:   Title( "title.asc" )
        object DESCENDING:  Title( "title.desc" )
    }

    sealed class VoteAverage( toString: String): Sorting( toString ),
            MovieSorting,       TvShowSorting,
            ListSorting,
            ListMovieSorting {
        object ASCENDING:   VoteAverage( "vote_average.asc" )
        object DESCENDING:  VoteAverage( "vote_average.desc" )
    }

    sealed class VoteCount( toString: String): Sorting( toString ),
            MovieSorting {
        object ASCENDING:   VoteCount( "vote_count.asc" )
        object DESCENDING:  VoteCount( "vote_count.desc" )
    }

    override fun toString() = toString
}