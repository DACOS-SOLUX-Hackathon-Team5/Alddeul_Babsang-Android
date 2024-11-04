package com.hackathon.alddeul_babsang.data.datasourceimpl

import com.hackathon.alddeul_babsang.data.datasource.ExampleDataSource
import com.hackathon.alddeul_babsang.data.dto.ExampleBaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetExampleDto
import com.sopt.data.service.ExampleApiService
import javax.inject.Inject

class ExampleDataSourceImpl @Inject constructor(
    private val exampleApiService: ExampleApiService
) : ExampleDataSource {
    override suspend fun getUsers(page: Int): ExampleBaseResponse<List<ResponseGetExampleDto>> {
        return exampleApiService.getUsers(page)
    }
}