package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.ReportDataSource
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportDto
import com.hackathon.alddeul_babsang.data.dto.request.RequestReportWriteDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.hackathon.alddeul_babsang.domain.repository.ReportRepository
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportDataSource: ReportDataSource
) : ReportRepository {
    override suspend fun postReports(
        userId: Long
    ): Result<List<ResponseReportDto>> {
        return runCatching {
            reportDataSource.postReports(
                requestReportDto = RequestReportDto(
                    userId = userId
                )
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
        imageUrl: String
    ): Result<String> {
        return runCatching {
            reportDataSource.postReportWrite(
                requestReportWriteDto = RequestReportWriteDto(
                    name = name,
                    category = category,
                    address = address,
                    contact = contact,
                    menuName1 = menuName1,
                    menuPrice1 = menuPrice1,
                    menuName2 = menuName2,
                    menuPrice2 = menuPrice2
                )
            ).result ?: ""
        }
    }
}