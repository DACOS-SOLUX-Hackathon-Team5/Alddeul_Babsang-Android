package com.hackathon.alddeul_babsang.data.service

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetReviewDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReviewDto
import com.sopt.data.service.ApiKeyStorage.ID
import com.sopt.data.service.ApiKeyStorage.REVIEW
import com.sopt.data.service.ApiKeyStorage.REVIEWS
import com.sopt.data.service.ApiKeyStorage.STORES
import com.sopt.data.service.ApiKeyStorage.STORE_ID
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface DetailApiService {
    @Multipart
    @POST("/$REVIEW/{$STORE_ID}")
    suspend fun postReview(
        @Path("storeId") storeId: Long,
        @PartMap data: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part reviewImage: MultipartBody.Part? = null
    ): BaseResponse<ResponseReviewDto>

    @GET("/$STORES/{$ID}/$REVIEWS")
    suspend fun getReviews(
        @Path("id") id: Long
    ): BaseResponse<ResponseGetReviewDto>

}