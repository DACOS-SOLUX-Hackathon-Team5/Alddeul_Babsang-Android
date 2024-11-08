package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto
import com.sopt.data.service.ApiKeyStorage.ID
import com.sopt.data.service.ApiKeyStorage.MAP
import com.sopt.data.service.ApiKeyStorage.STORES
import retrofit2.http.GET
import retrofit2.http.Path

interface MapApiService {
    @GET("/$MAP/$STORES")
    suspend fun getMapStores(): BaseResponse<List<ResponseMapStoresDto>>

    @GET("/$MAP/$STORES/{$ID}")
    suspend fun getMapStoreDetail(
        @Path("id") id: Long
    ): BaseResponse<ResponseMapStoreDetailDto>
}