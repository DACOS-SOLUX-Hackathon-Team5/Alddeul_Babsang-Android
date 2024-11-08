package com.hackathon.alddeul_babsang.data.datasourceimpl

import com.hackathon.alddeul_babsang.data.datasource.BabsangDataSource
import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.data.service.BabsangApiService
import javax.inject.Inject

class BabsangDataSourceImpl @Inject constructor(
    private val babsangApiService: BabsangApiService
) : BabsangDataSource {
    override suspend fun postStores(
        userId: Int
    ): BaseResponse<List<ResponseBabsangDto>> {
        return babsangApiService.postStores(userId)
    }
}