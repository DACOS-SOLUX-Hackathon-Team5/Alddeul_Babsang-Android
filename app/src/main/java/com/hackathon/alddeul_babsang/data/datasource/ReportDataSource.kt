package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto

interface ReportDataSource {
    suspend fun getReports(): BaseResponse<List<ResponseReportDto>>
}