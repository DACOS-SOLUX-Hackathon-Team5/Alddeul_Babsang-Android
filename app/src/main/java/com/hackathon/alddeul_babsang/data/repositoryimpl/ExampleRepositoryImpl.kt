package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.ExampleDataSource
import com.hackathon.alddeul_babsang.data.mapper.toExampleEntity
import com.hackathon.alddeul_babsang.domain.entity.ExampleEntity
import com.hackathon.alddeul_babsang.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) : ExampleRepository {
    override suspend fun getUsers(page: Int): Result<List<ExampleEntity>> {
        return runCatching {
            exampleDataSource.getUsers(page).data?.map { it.toExampleEntity() } ?: emptyList()
        }
    }
}