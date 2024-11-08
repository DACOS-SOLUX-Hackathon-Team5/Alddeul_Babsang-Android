package com.hackathon.alddeul_babsang.data.datasourceimpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hackathon.alddeul_babsang.app.di.UserPreferences
import com.hackathon.alddeul_babsang.data.datasource.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSourceImpl @Inject constructor(
    @UserPreferences private val dataStore: DataStore<Preferences>
) : UserPreferencesDataSource {
    private val USER_ACCESS_TOKEN = stringPreferencesKey("user_access_token")
    private val USER_REFRESH_TOKEN = stringPreferencesKey("user_refresh_token")
    private val CHECK_LOGIN = booleanPreferencesKey("check_login")

    override suspend fun saveUserAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[USER_ACCESS_TOKEN] = accessToken
        }
    }

    override fun getUserAccessToken(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ACCESS_TOKEN]
    }

    override suspend fun saveCheckLogin(checkLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[CHECK_LOGIN] = checkLogin
        }
    }

    override fun getCheckLogin(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[CHECK_LOGIN] ?: false
    }

    override suspend fun saveUserRefreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[USER_REFRESH_TOKEN] = refreshToken
        }
    }

    override fun getUserRefreshToken(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_REFRESH_TOKEN]
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ACCESS_TOKEN)
            preferences.remove(USER_REFRESH_TOKEN)
            preferences.remove(CHECK_LOGIN)
        }
    }
}