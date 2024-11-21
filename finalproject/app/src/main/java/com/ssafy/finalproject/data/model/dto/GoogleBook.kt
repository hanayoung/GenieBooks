package com.ssafy.finalproject.data.model.dto

import com.google.gson.annotations.SerializedName

data class GoogleBook(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo?,
    @SerializedName("saleInfo") val saleInfo: SaleInfo?,
    @SerializedName("accessInfo") val accessInfo: AccessInfo?,
    @SerializedName("searchInfo") val searchInfo: SearchInfo?
) {
    data class AccessInfo(
        @SerializedName("country") val country: String?
    )

    data class VolumeInfo(
        @SerializedName("title") val title: String,
        @SerializedName("authors") val authors: List<String>,
        @SerializedName("publisher") val publisher: String?,
        @SerializedName("publishedDate") val publishedDate: String,
        @SerializedName("description") val description: String?,
        @SerializedName("industryIdentifiers") val industryIdentifiers: List<IndustryIdentifier>,
        @SerializedName("categories") val categories: List<String>?,
        @SerializedName("imageLinks") val imageLinks: ImageLinks?,
    ) {
        data class ImageLinks(
            @SerializedName("thumbnail") val thumbnail: String?
        )

        data class IndustryIdentifier(
            @SerializedName("identifier") val identifier: String?
        )
    }

    data class SaleInfo(
        @SerializedName("listPrice") val listPrice: ListPrice?,
    ) {
        data class ListPrice(
            @SerializedName("amount") val amount: Int?,
            @SerializedName("currencyCode") val currencyCode: String?
        )
    }

    data class SearchInfo(
        @SerializedName("textSnippet") val textSnippet: String?
    )
}