package com.hackathon.alddeul_babsang.data.datasource

import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    suspend fun saveUserAccessToken(accessToken: String)
    fun getUserAccessToken(): Flow<String?>

    suspend fun saveCheckLogin(checkLogin: Boolean)
    fun getCheckLogin(): Flow<Boolean>

    suspend fun saveUserRefreshToken(refreshToken: String)
    fun getUserRefreshToken(): Flow<String?>

    suspend fun clear()
}