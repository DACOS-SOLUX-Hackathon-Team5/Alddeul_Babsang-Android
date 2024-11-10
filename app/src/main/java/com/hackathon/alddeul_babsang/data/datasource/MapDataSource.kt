package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto

interface MapDataSource {
    suspend fun getMapStores(): BaseResponse<List<ResponseMapStoresDto>>
    suspend fun postMapStoreDetail(
        id: Long,
        userId: Long,
    ): BaseResponse<ResponseMapStoreDetailDto>
}