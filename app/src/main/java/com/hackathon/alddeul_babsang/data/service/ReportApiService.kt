package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.sopt.data.service.ApiKeyStorage.POST
import com.sopt.data.service.ApiKeyStorage.REPORTS
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReportApiService {
    @GET("/$REPORTS")
    suspend fun getReports(): BaseResponse<List<ResponseReportDto>>

    @POST("/$REPORTS/$POST")
    suspend fun postReportWrite(
        @Body requestReportWriteDto: RequestReportWriteDto
    ): BaseResponse<String>
}