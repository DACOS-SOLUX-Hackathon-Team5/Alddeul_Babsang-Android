package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetReviewDto (
    @SerialName("reviewCnt") val reviewCnt: Int,
    @SerialName("reviewList") val reviewList: List<Review>
)

@Serializable
data class Review (
    @SerialName("nickname") val nickname: String,
    @SerialName("rate") val rate: Double,
    @SerialName("content") val content: String,
    @SerialName("image") val imageUrl: String? = null,
    @SerialName("date") val date: String
)