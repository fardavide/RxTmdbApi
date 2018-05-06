package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName


data class Company(
    @SerializedName("description")      val description: String?,
    @SerializedName("headquarters")     val headquarters: String,
    @SerializedName("homepage")         val homepage: String?,
    @SerializedName("id") override      val id: Int,
    @SerializedName("logo_path")        val logoPath: String?,
    @SerializedName("name") override    val name: String,
    @SerializedName("origin_country")   val originCountry: String,
    @SerializedName("parent_company")   val parentCompany: Company?

) : NamedIdElement