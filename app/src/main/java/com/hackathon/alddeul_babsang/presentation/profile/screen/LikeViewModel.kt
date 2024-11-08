package com.hackathon.alddeul_babsang.presentation.profile.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.data.dto.response.FavoriteRestaurantDto
import com.hackathon.alddeul_babsang.domain.entity.LikesEntity
import com.hackathon.alddeul_babsang.domain.repository.ProfileRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _getLikesState =
        MutableStateFlow<UiState<List<FavoriteRestaurantDto>>>(UiState.Empty)
    val getLikesState: StateFlow<UiState<List<FavoriteRestaurantDto>>> = _getLikesState

    fun getLikes() = viewModelScope.launch {
        _getLikesState.emit(UiState.Loading)
        profileRepository.getLikes().fold(
            onSuccess = {
                _getLikesState.emit(UiState.Success(it))
            },
            onFailure = {
                _getLikesState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    val mockLikes = listOf(
        LikesEntity(
            id = 1,
            avatar = null,
            name = "송이네 밥상",
            codeName = "기타외식업",
            address = "서울특별시 용산구 청파동 81",
            phone = "02-210-0120",
            favorite = true
        ),
        LikesEntity(
            id = 2,
            avatar = null,
            name = "송이네 일식",
            codeName = "경양식/일식",
            address = "서울특별시 용산구 청파동 11",
            phone = "02-210-0220",
            favorite = true
        ),
        LikesEntity(
            id = 3,
            avatar = null,
            name = "송이네 한식",
            codeName = "한식",
            address = "서울특별시 용산구 청파동 31",
            phone = "02-223-0220",
            favorite = true
        ),
        LikesEntity(
            id = 4,
            avatar = null,
            name = "송이네 중식",
            codeName = "중식",
            address = "서울특별시 용산구 청파동 31",
            phone = "02-223-0220",
            favorite = true
        ),
        LikesEntity(
            id = 4,
            avatar = "https://avatars.githubusercontent.com/u/166610834?s=400&u=568eacc2e4696d563a4fd732c148edba2196e4f6&v=4",
            name = "송이네 밥상",
            codeName = "중식",
            address = "서울특별시 용산구 청파동 31",
            phone = "02-223-0220",
            favorite = true
        )
    )
}