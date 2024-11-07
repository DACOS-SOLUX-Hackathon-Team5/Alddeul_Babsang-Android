package com.hackathon.alddeul_babsang.data.repositoryimpl

import com.hackathon.alddeul_babsang.data.datasource.UserPreferencesDataSource
import com.hackathon.alddeul_babsang.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override suspend fun saveUserAccessToken(accessToken: String) {
        dataSource.saveUserAccessToken(accessToken)
    }

    override fun getUserAccessToken(): Flow<String?> = dataSource.getUserAccessToken()

    override suspend fun saveCheckLogin(checkLogin: Boolean) {
        dataSource.saveCheckLogin(checkLogin)
    }

    override fun getCheckLogin(): Flow<Boolean> = dataSource.getCheckLogin()

    override suspend fun saveUserRefreshToken(refreshToken: String) {
        dataSource.saveUserRefreshToken(refreshToken)
    }

    override fun getUserRefreshToken(): Flow<String?> = dataSource.getUserRefreshToken()

    override suspend fun clear() = dataSource.clear()

}