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

    override suspend fun postLike(userId: Long, storeId: Long): Result<String> {
        return runCatching {
            profileDataSource.postLike(
                requestLikesDto = RequestLikesDto(
                    userId = userId,
                    storeId = storeId
                )
            ).result.toString()
        }
    }
}