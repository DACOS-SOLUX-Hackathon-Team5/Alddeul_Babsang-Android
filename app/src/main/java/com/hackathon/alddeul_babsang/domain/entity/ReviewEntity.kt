package com.hackathon.alddeul_babsang.domain.entity

data class ReviewEntity(
    val id: Long,
    val avatar: String,
    val nickname: String,
    val createdAt: String,
    val star: Double,
    val content: String
)