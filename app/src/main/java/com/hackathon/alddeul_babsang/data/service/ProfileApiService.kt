package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseLikesDto
import com.sopt.data.service.ApiKeyStorage.STORES
import com.sopt.data.service.ApiKeyStorage.USER_ID
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApiService {
    @GET("/$STORES/{$USER_ID}}")
    suspend fun getLikes(
        @Path("userId") userId: Long,
    ): BaseResponse<ResponseLikesDto>
}