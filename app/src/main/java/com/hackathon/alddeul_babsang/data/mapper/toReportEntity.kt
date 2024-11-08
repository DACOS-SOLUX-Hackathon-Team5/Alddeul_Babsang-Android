package com.hackathon.alddeul_babsang.data.mapper

import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.hackathon.alddeul_babsang.domain.entity.ReportEntity

class toLikesEntity {
}

fun ResponseReportDto.toLikesEntity() = ReportEntity(
    id, imageUrl, name, category, address, contact, isFavorite
)

