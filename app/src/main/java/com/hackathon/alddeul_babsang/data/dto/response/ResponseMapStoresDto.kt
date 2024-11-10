package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMapStoresDto (
    @SerialName("storeId") val storeId: Long,
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("address") val address: String,
    @SerialName("region") val region: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
)