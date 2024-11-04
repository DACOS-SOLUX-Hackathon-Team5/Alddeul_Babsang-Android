package com.hackathon.alddeul_babsang.data.mapper

import com.hackathon.alddeul_babsang.data.dto.response.ResponseGetExampleDto
import com.hackathon.alddeul_babsang.domain.entity.ExampleEntity

fun ResponseGetExampleDto.toExampleEntity() = ExampleEntity(
    email, firstName, avatar
)