package com.hackathon.alddeul_babsang.domain.entity

data class MapEntity (
    val id: Int,
    val name: String,
    val code: Int,
    val codeName: String,
    val address: String,
    val phone: String,
    val menu: String,
    val gu: String,
    val latitude: Double,
    val longitude: Double,
)