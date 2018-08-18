package studio.forface.rxtmdbapi.utils


operator fun <T> Iterable<T>?.plus(other: Iterable<T>?): List<T> =
        ( this ?: listOf() ).toMutableList().apply { addAll( other ?: listOf() ) }.toList()