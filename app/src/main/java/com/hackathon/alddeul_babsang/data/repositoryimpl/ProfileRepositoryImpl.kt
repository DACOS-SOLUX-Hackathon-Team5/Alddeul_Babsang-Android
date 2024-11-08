package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.ProfileDataSource
import com.hackathon.alddeul_babsang.data.dto.response.FavoriteRestaurantDto
import com.hackathon.alddeul_babsang.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getLikes(): Result<List<FavoriteRestaurantDto>> {
        return runCatching {
            profileDataSource.getLikes().result?.favoriteRestaurants ?: emptyList()
        }
    }
}