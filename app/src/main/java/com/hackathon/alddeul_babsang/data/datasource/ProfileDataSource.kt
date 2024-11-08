package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestLikesDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseLikesDto

interface ProfileDataSource {
    suspend fun getLikes(
        userId: Long
    ): BaseResponse<ResponseLikesDto>

    suspend fun postLike(
        requestLikesDto: RequestLikesDto
    ): BaseResponse<String>
}