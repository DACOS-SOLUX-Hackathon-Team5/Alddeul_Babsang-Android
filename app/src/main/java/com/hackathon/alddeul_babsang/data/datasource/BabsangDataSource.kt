package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto

interface BabsangDataSource {
    suspend fun getStores(): BaseResponse<List<ResponseBabsangDto>>
}