package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity
import com.hackathon.alddeul_babsang.domain.entity.LikesEntity
import com.hackathon.alddeul_babsang.domain.repository.BabsangRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BabsangViewModel @Inject constructor(
    private val babsangRepository: BabsangRepository
) : ViewModel() {
    private val _postBabsangState =
        MutableStateFlow<UiState<List<ResponseBabsangDto>>>(UiState.Empty)
    val postBabsangState: StateFlow<UiState<List<ResponseBabsangDto>>> = _postBabsangState

    fun postBabsang() = viewModelScope.launch  {
        _postBabsangState.emit(UiState.Loading)
        babsangRepository.postStores(userId = 1).fold(
            onSuccess = {
                _postBabsangState.emit(UiState.Success(it))
            },
            onFailure = {
                _postBabsangState.emit(UiState.Failure(it.message.toString()))
                Timber.e(it.localizedMessage)
            }
        )
    }

    val mockBabsangRecommendList = listOf(
        BabsangRecommendEntity(
            id = 1,
            avatar = "https://avatars.githubusercontent.com/u/166610834?s=400&u=568eacc2e4696d563a4fd732c148edba2196e4f6&v=4",
            name = "송이네 밥상",
            codeName = "경양식/일식",
            address = "용산구",
        ),
        BabsangRecommendEntity(
            id = 2,
            avatar = null,
            name = "송이네 밥상",
            codeName = "기타외식업",
            address = "중구",
        ),
        BabsangRecommendEntity(
            id = 3,
            avatar = null,
            name = "송이네 한식",
            codeName = "한식",
            address = "양천구",
        ),
        BabsangRecommendEntity(
            id = 3,
            avatar = null,
            name = "송이네 한식",
            codeName = "중식",
            address = "양천구",
        ),
        BabsangRecommendEntity(
            id = 1,
            avatar = null,
            name = "송이네 밥상",
            codeName = "경양식/일식",
            address = "용산구",
        )
    )
    val mockBabsang = listOf(
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