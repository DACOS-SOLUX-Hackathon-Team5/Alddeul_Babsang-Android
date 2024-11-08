package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ReportDataSource {
    suspend fun postReports(
        userId: Int
    ): BaseResponse<List<ResponseReportDto>>

    suspend fun postReportWrite(
        data: Map<String, RequestBody>,
        imageUrl: MultipartBody.Part? = null
    ): BaseResponse<String>
}