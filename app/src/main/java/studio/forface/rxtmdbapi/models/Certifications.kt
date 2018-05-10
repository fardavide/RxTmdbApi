package studio.forface.rxtmdbapi.models

import com.google.gson.annotations.SerializedName


data class CertificationsResult (

    @SerializedName("certifications") val certifications: Certifications

)


data class Certifications (

    @SerializedName("RU") val ru: List<ContentRating.Full>?,
    @SerializedName("US") val us: List<ContentRating.Full>?,
    @SerializedName("CA") val ca: List<ContentRating.Full>?,
    @SerializedName("AU") val au: List<ContentRating.Full>?,
    @SerializedName("FR") val fr: List<ContentRating.Full>?,
    @SerializedName("DE") val de: List<ContentRating.Full>?,
    @SerializedName("TH") val th: List<ContentRating.Full>?,
    @SerializedName("KR") val kr: List<ContentRating.Full>?,
    @SerializedName("GB") val gb: List<ContentRating.Full>?,
    @SerializedName("BR") val br: List<ContentRating.Full>?

)