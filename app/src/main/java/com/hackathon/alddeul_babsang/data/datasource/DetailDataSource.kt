package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetReviewDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReviewDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface DetailDataSource {
    suspend fun postReview(
        storeId: Long,
        data: Map<String, RequestBody>, // JSON 데이터를 포함하는 Map
        reviewImage: MultipartBody.Part? = null // 이미지 파일
    ): BaseResponse<ResponseReviewDto>

    suspend fun getReviews(
        id: Long
    ): BaseResponse<ResponseGetReviewDto>

    suspend fun postStoreDetail(
        id: Int,
        Userid: Int
    ): BaseResponse<ResponseDetailDto>

    suspend fun postRecommendStores(
        storeId: Int
    ): BaseResponse<List<ResponseDetailRecommendDto>>
}