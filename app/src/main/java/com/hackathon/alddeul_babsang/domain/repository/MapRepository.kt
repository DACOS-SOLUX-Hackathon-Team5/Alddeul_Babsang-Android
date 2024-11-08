package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto

interface MapRepository {
    suspend fun getMapStores(): Result<List<ResponseMapStoresDto>>
    suspend fun postMapStoreDetail(
        id: Long,
        userId: Long
    ): Result<ResponseMapStoreDetailDto?>
}