package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto
import com.sopt.data.service.ApiKeyStorage.RECOMMEND
import com.sopt.data.service.ApiKeyStorage.STORES
import retrofit2.http.POST
import retrofit2.http.Query

interface BabsangApiService {
    @POST("/$STORES/")
    suspend fun postStores(
        @Query("userId") userId: Int
    ): BaseResponse<List<ResponseBabsangDto>>

    @POST("/$RECOMMEND")
    suspend fun postRecommendStores(
        @Query("userId") userId: Int
    ) : BaseResponse<List<ResponseBabsangRecommendDto>>
}