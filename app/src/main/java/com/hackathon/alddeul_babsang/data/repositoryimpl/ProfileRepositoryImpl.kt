package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.ProfileDataSource
import com.hackathon.alddeul_babsang.data.dto.request.RequestLikesDto
import com.hackathon.alddeul_babsang.data.dto.response.FavoriteRestaurantDto
import com.hackathon.alddeul_babsang.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getLikes(
        userId: Long,
    ): Result<List<FavoriteRestaurantDto>> {
        return runCatching {
            profileDataSource.getLikes(
                userId = userId
            ).result?.favoriteRestaurants ?: emptyList()
        }
    }
}