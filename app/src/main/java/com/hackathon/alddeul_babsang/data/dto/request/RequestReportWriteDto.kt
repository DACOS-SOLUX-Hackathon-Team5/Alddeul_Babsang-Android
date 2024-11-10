package com.hackathon.alddeul_babsang.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestReportWriteDto (
    val name : String,
    val category: String,
    val address: String,
    val contact: String,
    val menuName1: String,
    val menuPrice1: Int,
    val menuName2: String,
    val menuPrice2: Int,
    val imageUrl: String? = null
)