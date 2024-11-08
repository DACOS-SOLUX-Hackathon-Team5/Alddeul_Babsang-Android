package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class ResponseBabsangDto (
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("address") val address: String,
    @SerialName("contact") val contact: String,
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("favorite") val favorite: Boolean,
)