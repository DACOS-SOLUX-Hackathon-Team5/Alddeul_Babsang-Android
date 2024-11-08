package com.hackathon.alddeul_babsang.data.datasource

import com.hackathon.alddeul_babsang.data.dto.BaseResponse
import com.hackathon.alddeul_babsang.data.dto.response.ResponseLikesDto

interface ProfileDataSource {
    suspend fun getLikes(): BaseResponse<ResponseLikesDto>
}