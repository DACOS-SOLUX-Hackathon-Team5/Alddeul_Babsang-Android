package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto

interface BabsangRepository {
    suspend fun postStores(
        userId: Int
    ): Result<List<ResponseBabsangDto>>

    suspend fun postRecommendStores(
        userId: Int
    ): Result<List<ResponseBabsangRecommendDto>>
}