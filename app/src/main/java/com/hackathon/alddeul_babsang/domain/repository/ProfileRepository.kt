package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.FavoriteRestaurantDto

interface ProfileRepository {
    suspend fun getLikes(
        userId: Long,
    ): Result<List<FavoriteRestaurantDto>>
}