package com.sopt.data.service

import com.hackathon.alddeul_babsang.data.dto.ExampleBaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetExampleDto
import com.sopt.data.service.ApiKeyStorage.API
import com.sopt.data.service.ApiKeyStorage.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleApiService {
    @GET("/$API/$USERS")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ExampleBaseResponse<List<ResponseGetExampleDto>>
}