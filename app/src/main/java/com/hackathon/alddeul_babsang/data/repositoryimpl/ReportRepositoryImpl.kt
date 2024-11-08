package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.ReportDataSource
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.hackathon.alddeul_babsang.domain.repository.ReportRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportDataSource: ReportDataSource
) : ReportRepository {
    override suspend fun postReports(
        userId: Int
    ): Result<List<ResponseReportDto>> {
        return runCatching {
            reportDataSource.postReports(
                userId = userId
            ).result ?: emptyList()
        }
    }

    override suspend fun postReportWrite(
        name: String,
        category: String,
        address: String,
        contact: String,
        menuName1: String,
        menuPrice1: Int,
        menuName2: String,
        menuPrice2: Int,
        imageUrl: File
    ): Result<String> {
        return runCatching {
            val dataMap = mapOf(
                "name" to name.toRequestBody("text/plain".toMediaTypeOrNull()),
                "category" to category.toRequestBody("text/plain".toMediaTypeOrNull()),
                "address" to address.toRequestBody("text/plain".toMediaTypeOrNull()),
                "contact" to contact.toRequestBody("text/plain".toMediaTypeOrNull()),
                "menuName1" to menuName1.toRequestBody("text/plain".toMediaTypeOrNull()),
                "menuPrice1" to menuPrice1.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                "menuName2" to menuName2.toRequestBody("text/plain".toMediaTypeOrNull()),
                "menuPrice2" to menuPrice2.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            )

            // 이미지 파일 파트 생성
            val filePart = imageUrl?.let {
                val requestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("imageUrl", it.name, requestBody)
            }

            reportDataSource.postReportWrite(
                data = dataMap,
                imageUrl = filePart
            ).message
        }
    }
}