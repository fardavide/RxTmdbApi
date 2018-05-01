package studio.forface.rxtmdbapi.tmdb.models

import com.google.gson.annotations.SerializedName
data class ProductionCompany(

    @SerializedName("id") override      val id: Int,
    @SerializedName("logo_path")        val logoPath: String?,
    @SerializedName("name") override    val name: String,
    @SerializedName("origin_country")   val originCountry: String

): NamedIdElement