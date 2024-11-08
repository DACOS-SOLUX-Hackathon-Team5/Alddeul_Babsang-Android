package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseReviewDto (
    @SerialName("storeId") val storeId: Long,
    @SerialName("userId") val userId: Long,
    @SerialName("message") val message: String,
)