package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.BabsangDataSource
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.domain.repository.BabsangRepository
import javax.inject.Inject

class BabsangRepositoryImpl @Inject constructor(
    private val babsangDataSource: BabsangDataSource
) : BabsangRepository{
    override suspend fun postStores(
        userId: Int
    ): Result<List<ResponseBabsangDto>> {
        return runCatching {
            babsangDataSource.postStores(
                userId = userId
            ).result ?: emptyList()
        }
    }
}