package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestLikesDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseLikesDto
import com.sopt.data.service.ApiKeyStorage.FAVORITES
import com.sopt.data.service.ApiKeyStorage.USER_ID
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileApiService {
    @GET("/$FAVORITES/{$USER_ID}")
    suspend fun getLikes(
        @Path("userId") userId: Int,
    ): BaseResponse<ResponseLikesDto>

    @POST("/$FAVORITES")
    suspend fun postLike(
        @Body requestLikesDto: RequestLikesDto
    ): BaseResponse<String>
}