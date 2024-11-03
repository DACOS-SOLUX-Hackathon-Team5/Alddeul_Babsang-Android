package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.ExampleBaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetExampleDto

interface ExampleDataSource {
    suspend fun getUsers(page: Int): ExampleBaseResponse<List<ResponseGetExampleDto>>
}