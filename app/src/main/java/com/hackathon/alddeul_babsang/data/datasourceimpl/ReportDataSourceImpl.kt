package com.hackathon.alddeul_babsang.data.datasourceimpl

import com.hackathon.alddeul_babsang.data.datasource.ReportDataSource
import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportDto
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.hackathon.alddeul_babsang.data.service.ReportApiService
import javax.inject.Inject

class ReportDataSourceImpl @Inject constructor(
    private val reportApiService: ReportApiService
) : ReportDataSource {
    override suspend fun postReports(requestReportDto: RequestReportDto): BaseResponse<List<ResponseReportDto>> {
        return reportApiService.postReports(requestReportDto)
    }

    override suspend fun postReportWrite(requestReportWriteDto: RequestReportWriteDto): BaseResponse<String> {
        return reportApiService.postReportWrite(requestReportWriteDto)
}}