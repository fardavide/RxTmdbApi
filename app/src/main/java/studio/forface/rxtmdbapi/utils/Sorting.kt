package studio.forface.rxtmdbapi.utils


sealed class Sorting (private val toString: String ) {

    sealed class CreationDate( toString: String ) : Sorting( toString ) {
        object ASCENDING: CreationDate( "created_at.asc" )
        object DESCENDING: CreationDate( "created_at.desc" )
    }


    override fun toString() = toString

}