package studio.forface.rxtmdbapi.tmdb.models


interface IdElement {
    val id: Int
}

interface NamedElement {
    val name: String
}

interface NamedIdElement: IdElement, NamedElement


interface Pageable