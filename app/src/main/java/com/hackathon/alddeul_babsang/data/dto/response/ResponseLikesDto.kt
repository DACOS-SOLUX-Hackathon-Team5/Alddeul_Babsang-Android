package com.hackathon.alddeul_babsang.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLikesDto(
    @SerialName("favoriteStoreDetailDtos") val favoriteStoreDetailDtos: List<FavoriteRestaurantDto>
)

@Serializable
data class FavoriteRestaurantDto(
    @SerialName("restaurantId") val restaurantId: Long,
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("address") val address: String,
    @SerialName("contact") val contact: String,
    @SerialName("restaurantImageUrl") val restaurantImageUrl: String? = null,
    @SerialName("favorite") val favorite: Boolean
)