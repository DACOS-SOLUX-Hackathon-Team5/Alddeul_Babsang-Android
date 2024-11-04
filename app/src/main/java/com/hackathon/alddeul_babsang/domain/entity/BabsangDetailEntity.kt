package com.hackathon.alddeul_babsang.domain.entity

data class BabsangDetailEntity(
    val id: Long,
    val avatar: String? = null,
    val name: String,
    val codeName: String,
    val address: String,
    val phone: String,
    val rating: Double,
    val menu: String,
    val review: List<ReviewEntity>
)