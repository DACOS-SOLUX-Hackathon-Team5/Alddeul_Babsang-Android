package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailRecommendDto
import com.hackathon.alddeul_babsang.data.dto.response.Review
import com.hackathon.alddeul_babsang.domain.entity.BabsangDetailEntity
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity
import com.hackathon.alddeul_babsang.domain.entity.MenuEntity
import com.hackathon.alddeul_babsang.domain.entity.ReviewEntity
import com.hackathon.alddeul_babsang.domain.repository.DetailRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    private val _getReviewsState = MutableStateFlow<UiState<List<Review>>>(UiState.Empty)
    val getReviewsState: StateFlow<UiState<List<Review>>> = _getReviewsState

    private val _postDetailState = MutableSharedFlow<UiState<ResponseDetailDto?>>(replay = 1)
    val postDetailState: SharedFlow<UiState<ResponseDetailDto?>> = _postDetailState

    private val _postDetailRecommendState =
        MutableStateFlow<UiState<List<ResponseDetailRecommendDto?>>>(UiState.Empty)
    val postDetailRecommendState: StateFlow<UiState<List<ResponseDetailRecommendDto?>>> = _postDetailRecommendState

    fun getReviews(id: Long) = viewModelScope.launch {
        _getReviewsState.emit(UiState.Loading)
        detailRepository.getReviews(id).fold(
            onSuccess = {
                _getReviewsState.emit(UiState.Success(it))
            },
            onFailure = {
                _getReviewsState.emit(UiState.Failure(it.message ?: ""))
            }
        )
    }

    fun postDetail(id: Int) = viewModelScope.launch {
        _postDetailState.emit(UiState.Loading)
        detailRepository.postStoreDetail(id = id, userId = 1).fold(
            onSuccess = {
                _postDetailState.emit(UiState.Success(it))
            },
            onFailure = {
                _postDetailState.emit(UiState.Failure(it.message ?: ""))
            }
        )
    }


    fun postDetailRecommend(storeId: Int) = viewModelScope.launch  {
        _postDetailRecommendState.emit(UiState.Loading)
        detailRepository.postRecommendStores(storeId = storeId).fold(
            onSuccess = {
                _postDetailRecommendState.emit(UiState.Success(it))
            },
            onFailure = {
                _postDetailRecommendState.emit(UiState.Failure(it.message.toString()))
                Timber.e(it.localizedMessage)
            }
        )
    }




    val mockMenuList = listOf(
        MenuEntity("김치찌개", 8000),
        MenuEntity("된장찌개", 9000),
    )

    val mockReviews = listOf(
        ReviewEntity(
            id = 1,
            avatar = "https://avatars.githubusercontent.com/u/12345678?v=4",
            nickname = "김개발",
            createdAt = "24.03.12",
            star = 4.56,
            content = "맛있어요!"
        ),
        ReviewEntity(
            id = 2,
            avatar = "https://avatars.githubusercontent.com/u/12345678?v=4",
            nickname = "김디자인",
            createdAt = "24.03.12",
            star = 3.28,
            content = "그저 그랬어요 블라블라블라!"
        ),
        ReviewEntity(
            id = 3,
            avatar = "https://avatars.githubusercontent.com/u/12345678?v=4",
            nickname = "김마케팅",
            createdAt = "24.03.12",
            star = 2.49,
            content = "위생이 별로에요!"
        ),
    )

    val mockDetail = BabsangDetailEntity(
        id = 1,
        name = "송이네 밥상",
        codeName = "한식",
        address = "서울특별시 용산구 청파동 11",
        phone = "02-210-0220",
        rating = 4.5,
        menu = "김치찌개: 8000, 된장찌개 9000",
        review = listOf(
            ReviewEntity(
                id = 1,
                avatar = "",
                nickname = "김철수",
                star = 4.5,
                content = "맛있어요",
                createdAt = "2021-10-10"
            )
        ),
        isFavorite = true
    )
    val mockDetailRecommend = listOf(
        BabsangRecommendEntity(
            id = 1,
            avatar = null,
            name = "족발 야시장",
            codeName = "한식",
            address = "용산 동자동",
        ),
        BabsangRecommendEntity(
            id = 2,
            avatar = null,
            name = "족발 야시장",
            codeName = "경양식/일식",
            address = "용산 동자동",
        ),
        BabsangRecommendEntity(
            id = 3,
            avatar = null,
            name = "족발 야시장",
            codeName = "중식",
            address = "용산 동자동",
        ),
        BabsangRecommendEntity(
            id = 4,
            avatar = null,
            name = "족발 야시장",
            codeName = "기타외식업",
            address = "용산 동자동",
        ),
    )
}