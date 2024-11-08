package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.Review
import java.io.File

interface DetailRepository {
    suspend fun postReview(
        storeId: Long,
        userId: Long,
        rating: Double,
        content: String,
        reviewImage: File
    ): Result<String>

    suspend fun getReviews(
        id: Long
    ): Result<List<Review>>

    suspend fun postStoreDetail(
        id: Int,
        userId: Int
    ): Result<ResponseDetailDto?>

    suspend fun postRecommendStores(
        storeId: Int
    ): Result<List<ResponseDetailRecommendDto?>>
}