package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportDto
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReviewDto
import com.sopt.data.service.ApiKeyStorage.POST
import com.sopt.data.service.ApiKeyStorage.REPORTS
import com.sopt.data.service.ApiKeyStorage.REVIEW
import com.sopt.data.service.ApiKeyStorage.STORE_ID
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ReportApiService {
    @POST("/$REPORTS/")
    suspend fun postReports(
        @Query("userId") userId: Int
    ): BaseResponse<List<ResponseReportDto>>

    @Multipart
    @POST("/$REPORTS/$POST")
    suspend fun postReportWrite(
        @PartMap data: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imageUrl: MultipartBody.Part? = null
    ): BaseResponse<String>
}