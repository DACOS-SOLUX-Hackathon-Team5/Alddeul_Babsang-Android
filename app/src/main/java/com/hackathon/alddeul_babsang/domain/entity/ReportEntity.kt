package com.hackathon.alddeul_babsang.domain.entity

data class ReportEntity(
    val id: Long,
    val avatar: String? = null,
    val name: String,
    val codeName: String,
    val address: String,
    val phone: String,
    val favorite: Boolean = false,
)