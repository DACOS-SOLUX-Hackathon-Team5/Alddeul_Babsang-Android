package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.sopt.data.service.ApiKeyStorage.STORES
import retrofit2.http.GET

interface BabsangApiService {
    @GET("/$STORES")
    suspend fun getStores(): BaseResponse<List<ResponseBabsangDto>>
}