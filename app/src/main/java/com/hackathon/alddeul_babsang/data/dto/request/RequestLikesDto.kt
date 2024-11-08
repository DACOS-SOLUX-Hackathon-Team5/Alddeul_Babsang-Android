package com.hackathon.alddeul_babsang.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLikesDto (
    @SerialName("userId") val userId: Long,
    @SerialName("storeId") val storeId: Long
)