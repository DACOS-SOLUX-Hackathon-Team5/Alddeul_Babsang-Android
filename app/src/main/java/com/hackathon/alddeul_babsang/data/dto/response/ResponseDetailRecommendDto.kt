package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDetailRecommendDto (
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("region") val region: String,
    @SerialName("storeId") val storeId: Long,
)