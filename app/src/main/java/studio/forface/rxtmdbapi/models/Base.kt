package studio.forface.rxtmdbapi.models


interface IdElement {
    val id: Int
}

interface StringIdElement {
    val id: String
}

interface NamedElement {
    val name: String
}

interface NamedIdElement: IdElement, NamedElement

interface NamedStringIdElement: StringIdElement, NamedElement


interface Pageable