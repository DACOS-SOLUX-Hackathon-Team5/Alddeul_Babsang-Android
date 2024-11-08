package com.hackathon.alddeul_babsang.data.datasourceimpl

import com.hackathon.alddeul_babsang.data.datasource.MapDataSource
import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto
import com.hackathon.alddeul_babsang.data.service.MapApiService
import javax.inject.Inject

class MapDataSourceImpl @Inject constructor(
    private val mapApiService: MapApiService
) : MapDataSource {
    override suspend fun getMapStores(): BaseResponse<List<ResponseMapStoresDto>> {
        return mapApiService.getMapStores()
    }

    override suspend fun postMapStoreDetail(
        id: Long,
        requestMapStoreDetailDto: RequestMapStoreDetailDto
    ): BaseResponse<ResponseMapStoreDetailDto> {
        return mapApiService.postMapStoreDetail(id, requestMapStoreDetailDto)
    }
}