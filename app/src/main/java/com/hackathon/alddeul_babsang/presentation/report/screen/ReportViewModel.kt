package com.hackathon.alddeul_babsang.presentation.report.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.data.dto.response.ResponseReportDto
import com.hackathon.alddeul_babsang.domain.entity.ReportEntity
import com.hackathon.alddeul_babsang.domain.repository.ReportRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportRepository: ReportRepository
) : ViewModel() {
    private val _getReportState =
        MutableStateFlow<UiState<List<ResponseReportDto>>>(UiState.Empty)
    val getReportState: StateFlow<UiState<List<ResponseReportDto>>> = _getReportState

    private val _postReportWriteState =
        MutableStateFlow<UiState<String>>(UiState.Empty)
    val postReportWriteState: StateFlow<UiState<String>> = _postReportWriteState


    fun getReport(
        userId : Long = 1
    ) = viewModelScope.launch  {
        _getReportState.emit(UiState.Loading)
        reportRepository.getReports(userId).fold(
            onSuccess = {
                _getReportState.emit(UiState.Success(it))
            },
            onFailure = {
                _getReportState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun postReportWrite(
        name: String,
        category: String,
        address: String,
        contact: String,
        menuName1: String,
        menuPrice1: Int,
        menuName2: String,
        menuPrice2: Int,
        imageUrl: String
    ) = viewModelScope.launch  {
        _postReportWriteState.emit(UiState.Loading)
        reportRepository.postReportWrite(
            name, category, address, contact, menuName1, menuPrice1, menuName2, menuPrice2, imageUrl
        ).fold(
            onSuccess = {
                _postReportWriteState.emit(UiState.Success(it))
            },
            onFailure = {
                _postReportWriteState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }



    val mockReportBabsang = listOf(
        ReportEntity(
            id = 1,
            avatar = null,
            name = "송이네 밥상",
            codeName = "기타외식업",
            address = "서울특별시 용산구 청파동 81",
            phone = "02-210-0120",
            favorite = true
        ),
        ReportEntity(
            id = 2,
            avatar = null,
            name = "송이네 일식",
            codeName = "경양식/일식",
            address = "서울특별시 용산구 청파동 11",
            phone = "02-210-0220",
            favorite = true
        ),
        ReportEntity(
            id = 3,
            avatar = null,
            name = "송이네 한식",
            codeName = "한식",
            address = "서울특별시 용산구 청파동 31",
            phone = "02-223-0220",
            favorite = true
        ),
        ReportEntity(
            id = 4,
            avatar = null,
            name = "송이네 중식",
            codeName = "중식",
            address = "서울특별시 용산구 청파동 31",
            phone = "02-223-0220",
            favorite = true
        ),
        ReportEntity(
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