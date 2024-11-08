package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto

interface BabsangRepository {
    suspend fun postStores(
        userId: Int
    ): Result<List<ResponseBabsangDto>>
}