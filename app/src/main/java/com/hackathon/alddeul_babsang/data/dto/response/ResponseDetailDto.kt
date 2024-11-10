package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDetailDto(
    @SerialName("storeInfo") val storeInfo: StoreInfo,
    @SerialName("aveRating") val aveRating: Double,
    @SerialName("menu1") val menu1: Menu,
    @SerialName("menu2") val menu2: Menu
)

@Serializable
data class StoreInfo(
    @SerialName("storeId") val storeId: Long,
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("address") val address: String,
    @SerialName("contact") val contact: String,
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("favorite") val favorite: Boolean
)

@Serializable
data class Menu(
    @SerialName("name") val name: String,
    @SerialName("price") val price: Int
)