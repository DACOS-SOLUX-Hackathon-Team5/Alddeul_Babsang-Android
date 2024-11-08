package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import java.io.File

interface ReportRepository {
    suspend fun postReports(
        userId: Int
    ): Result<List<ResponseReportDto>>

    suspend fun postReportWrite(
        name: String,
        category: String,
        address: String,
        contact: String,
        menuName1: String,
        menuPrice1: Int,
        menuName2: String,
        menuPrice2: Int,
        imageUrl: File
    ): Result<String>

}

