package com.hackathon.alddeul_babsang.domain.repository

import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto

interface ReportRepository {
    suspend fun getReports(): Result<List<ResponseReportDto>>
}