package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMapStoreDetailDto (
    @SerialName("storeId") val storeId: Long,
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("address") val address: String,
    @SerialName("contact") val contact: String,
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("favorite") val favorite: Boolean,
)