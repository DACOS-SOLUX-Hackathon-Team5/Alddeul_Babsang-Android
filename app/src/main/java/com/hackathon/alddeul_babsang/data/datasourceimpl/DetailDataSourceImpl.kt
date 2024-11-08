package com.hackathon.alddeul_babsang.data.datasourceimpl

import com.hackathon.alddeul_babsang.data.datasource.DetailDataSource
import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetReviewDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReviewDto
import com.hackathon.alddeul_babsang.data.service.DetailApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class DetailDataSourceImpl @Inject constructor(
    private val detailApiService: DetailApiService
) : DetailDataSource {
    override suspend fun postReview(
        storeId: Long,
        data: Map<String, RequestBody>,
        reviewImage: MultipartBody.Part?
    ): BaseResponse<ResponseReviewDto> {
        return detailApiService.postReview(storeId, data, reviewImage)
    }

    override suspend fun getReviews(id: Long): BaseResponse<ResponseGetReviewDto> {
        return detailApiService.getReviews(id)
    }

    override suspend fun postStoreDetail(id: Int, Userid: Int): BaseResponse<ResponseDetailDto> {
        return detailApiService.postStoreDetail(id, Userid)
    }

}