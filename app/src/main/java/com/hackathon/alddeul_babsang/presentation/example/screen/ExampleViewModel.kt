package com.hackathon.alddeul_babsang.presentation.example.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.domain.entity.ExampleEntity
import com.hackathon.alddeul_babsang.domain.repository.ExampleRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    val exampleRepository: ExampleRepository
) : ViewModel() {
    private val _getExampleState = MutableStateFlow<UiState<List<ExampleEntity>>>(UiState.Empty)
    val getExampleState: StateFlow<UiState<List<ExampleEntity>>> = _getExampleState

    init {
        getUsers(PAGE)
    }

    fun getUsers(page: Int) = viewModelScope.launch {
        _getExampleState.emit(UiState.Loading)
        exampleRepository.getUsers(page).fold(
            {
                if (it.isNotEmpty()) _getExampleState.emit(UiState.Success(it)) else _getExampleState.emit(
                    UiState.Failure("400")
                )
            },
            { _getExampleState.emit(UiState.Failure(it.message.toString())) }
        )
    }

    companion object {
        private const val PAGE = 2
    }
}