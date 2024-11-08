package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.sopt.data.service.ApiKeyStorage.REPORTS
import retrofit2.http.GET

interface ReportApiService {
    @GET("/$REPORTS")
    suspend fun getREPORTS(): BaseResponse<List<ResponseReportDto>>
}