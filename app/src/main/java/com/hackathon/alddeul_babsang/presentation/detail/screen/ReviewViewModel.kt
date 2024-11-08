package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.domain.repository.DetailRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    private val _postReviewState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val postReviewState: StateFlow<UiState<String>> = _postReviewState

    fun postReview(
        storeId: Long,
        userId: Long,
        rating: Double,
        content: String,
        reviewImage: File
    ) = viewModelScope.launch {
        _postReviewState.emit(UiState.Loading)
        detailRepository.postReview(storeId, userId, rating, content, reviewImage).fold(
            onSuccess = {
                _postReviewState.emit(UiState.Success(it))
            },
            onFailure = {
                _postReviewState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }
}