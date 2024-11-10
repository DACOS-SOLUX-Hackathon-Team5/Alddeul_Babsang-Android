package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.domain.entity.ExampleEntity

interface ExampleRepository {
    suspend fun getUsers(page: Int): Result<List<ExampleEntity>>
}