package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto

interface BabsangDataSource {
    suspend fun postStores(
        userId: Int
    ): BaseResponse<List<ResponseBabsangDto>>

    suspend fun postRecommendStores(
        userId: Int
    ): BaseResponse<List<ResponseBabsangRecommendDto>>
}