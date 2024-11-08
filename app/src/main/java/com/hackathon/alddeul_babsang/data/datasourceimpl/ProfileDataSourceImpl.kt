package com.hackathon.alddeul_babsang.data.datasourceimpl

import com.hackathon.alddeul_babsang.data.datasource.ProfileDataSource
import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.request.RequestLikesDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseLikesDto
import com.hackathon.alddeul_babsang.data.service.ProfileApiService
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val profileApiService: ProfileApiService
) : ProfileDataSource {
    override suspend fun getLikes(
        userId: Long
    ): BaseResponse<ResponseLikesDto> {
        return profileApiService.getLikes(userId)
    }

    override suspend fun postLike(requestLikesDto: RequestLikesDto): BaseResponse<String> {
        return profileApiService.postLike(requestLikesDto)
    }
}