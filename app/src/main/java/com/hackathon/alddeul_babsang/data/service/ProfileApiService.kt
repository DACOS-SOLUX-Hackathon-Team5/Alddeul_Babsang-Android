package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseLikesDto
import com.sopt.data.service.ApiKeyStorage.STORES
import retrofit2.http.GET

interface ProfileApiService {
    @GET("/$STORES")
    suspend fun getLikes(): BaseResponse<ResponseLikesDto>
}