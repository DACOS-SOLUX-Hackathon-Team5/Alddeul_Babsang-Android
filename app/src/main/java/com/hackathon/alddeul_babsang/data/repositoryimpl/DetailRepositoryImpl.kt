package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.DetailDataSource
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.Review
import com.hackathon.alddeul_babsang.domain.repository.DetailRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailDataSource: DetailDataSource
) : DetailRepository {
    override suspend fun postReview(
        storeId: Long,
        userId: Long,
        rating: Double,
        content: String,
        reviewImage: File
    ): Result<String> {
        return runCatching {
            val dataMap = mapOf(
                "userId" to userId.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                "rating" to rating.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                "content" to content.toRequestBody("text/plain".toMediaTypeOrNull())
            )

            // 이미지 파일 파트 생성
            val filePart = reviewImage?.let {
                val requestBody = it.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("reviewImage", it.name, requestBody)
            }

            detailDataSource.postReview(
                storeId = storeId,
                data = dataMap,
                reviewImage = filePart
            ).message
        }
    }

    override suspend fun getReviews(id: Long): Result<List<Review>> {
        return runCatching {
            detailDataSource.getReviews(id).result?.reviewList ?: emptyList()
        }
    }

    override suspend fun postStoreDetail(id: Int, userId: Int): Result<ResponseDetailDto?> {
        return runCatching {
            detailDataSource.postStoreDetail(id = id, Userid = userId).result
        }
    }

    override suspend fun postRecommendStores(
        storeId: Int
    ): Result<List<ResponseDetailRecommendDto?>> {
        return runCatching {
            detailDataSource.postRecommendStores(
                storeId = storeId
            ).result ?: emptyList()
        }
    }
}