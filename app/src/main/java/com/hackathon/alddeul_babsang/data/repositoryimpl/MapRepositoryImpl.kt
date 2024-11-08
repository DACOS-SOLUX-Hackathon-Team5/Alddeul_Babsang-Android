package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.MapDataSource
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto
import com.hackathon.alddeul_babsang.domain.repository.MapRepository
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapDataSource: MapDataSource
) : MapRepository {
    override suspend fun getMapStores(): Result<List<ResponseMapStoresDto>> {
        return runCatching {
            mapDataSource.getMapStores().result ?: emptyList()
        }
    }

    override suspend fun getMapStoreDetail(id: Long): Result<ResponseMapStoreDetailDto?> {
        return runCatching {
            mapDataSource.getMapStoreDetail(id).result
        }
    }
}