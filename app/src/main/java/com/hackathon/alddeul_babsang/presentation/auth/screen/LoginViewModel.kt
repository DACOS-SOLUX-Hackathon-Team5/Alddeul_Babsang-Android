package com.hackathon.alddeul_babsang.presentation.auth.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> = _accessToken

    private val _refreshToken = MutableStateFlow<String?>(null)
    val refreshToken: StateFlow<String?> = _refreshToken

    fun getUserAccessToken() = userPreferencesRepository.getUserAccessToken()

    fun saveUserAccessToken(accessToken: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveUserAccessToken(accessToken)
        }
    }

    fun getCheckLogin() = userPreferencesRepository.getCheckLogin()

    fun saveCheckLogin(checkLogin: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.saveCheckLogin(checkLogin)
        }
    }

    fun saveUserRefreshToken(refreshToken: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveUserRefreshToken(refreshToken)
        }
    }

    fun getUserRefreshToken() = userPreferencesRepository.getUserRefreshToken()

    fun clear() {
        viewModelScope.launch {
            userPreferencesRepository.clear()
        }
    }
}