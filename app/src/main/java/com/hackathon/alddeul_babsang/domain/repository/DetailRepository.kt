package com.hackathon.alddeul_babsang.domain.repository

import java.io.File

interface DetailRepository {
    suspend fun postReview(
        storeId: Long,
        userId: Long,
        rating: Double,
        content: String,
        reviewImage: File
    ): Result<String>
}