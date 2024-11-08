package com.hackathon.alddeul_babsang.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestMapStoreDetailDto (
    @SerialName("userId") val userId: Long,
)