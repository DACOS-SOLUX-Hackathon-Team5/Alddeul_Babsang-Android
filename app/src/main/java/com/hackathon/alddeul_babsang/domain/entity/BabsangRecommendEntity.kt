package com.hackathon.alddeul_babsang.domain.entity

data class BabsangRecommendEntity(
    val id: Long,
    val avatar: String? = null,
    val name: String,
    val codeName: String,
    val address: String,
)