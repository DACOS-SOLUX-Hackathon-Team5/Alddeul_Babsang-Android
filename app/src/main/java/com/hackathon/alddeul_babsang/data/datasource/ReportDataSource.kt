package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportDto
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto

interface ReportDataSource {
    suspend fun getReports(
        requestReportDto: RequestReportDto
    ): BaseResponse<List<ResponseReportDto>>

    suspend fun postReportWrite(
        requestReportWriteDto: RequestReportWriteDto
    ): BaseResponse<String>
}