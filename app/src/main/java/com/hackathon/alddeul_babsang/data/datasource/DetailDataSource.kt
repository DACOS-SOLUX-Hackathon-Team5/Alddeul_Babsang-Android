package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReviewDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface DetailDataSource {
    suspend fun postReview(
        storeId: Long,
        data: Map<String, RequestBody>, // JSON 데이터를 포함하는 Map
        reviewImage: MultipartBody.Part? = null // 이미지 파일
    ): BaseResponse<ResponseReviewDto>
}