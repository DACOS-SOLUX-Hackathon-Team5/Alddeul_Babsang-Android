package com.hackathon.alddeul_babsang.presentation.map.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoreDetailDto
import com.hackathon.alddeul_babsang.data.dto.response.ResponseMapStoresDto
import com.hackathon.alddeul_babsang.domain.entity.MapEntity
import com.hackathon.alddeul_babsang.domain.repository.MapRepository
import com.hackathon.alddeul_babsang.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {
    private val _getMapStoresState =
        MutableStateFlow<UiState<List<ResponseMapStoresDto>>>(UiState.Empty)
    val getMapStoresState: StateFlow<UiState<List<ResponseMapStoresDto>>> = _getMapStoresState

    private val _getMapStoreDetailState =
        MutableStateFlow<UiState<ResponseMapStoreDetailDto?>>(UiState.Empty)
    val getMapStoreDetailState: StateFlow<UiState<ResponseMapStoreDetailDto?>> =
        _getMapStoreDetailState

    fun getMapStores() = viewModelScope.launch {
        _getMapStoresState.emit(UiState.Loading)
        mapRepository.getMapStores().fold(
            onSuccess = {
                _getMapStoresState.emit(UiState.Success(it))
            },
            onFailure = {
                _getMapStoresState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    fun getMapStoreDetail(id: Long) = viewModelScope.launch {
        _getMapStoreDetailState.emit(UiState.Loading)
        mapRepository.getMapStoreDetail(id).fold(
            onSuccess = {
                _getMapStoreDetailState.emit(UiState.Success(it))
            },
            onFailure = {
                _getMapStoreDetailState.emit(UiState.Failure(it.message.toString()))
            }
        )
    }

    val mockMapList = listOf(
        MapEntity(
            id = 1,
            name = "무진장",
            code = 3,
            codeName = "경양식/일식",
            address = "서울특별시 용산구 원효로 212",
            phone = "02-712-8814",
            menu = "알탕: 10000원, 생선구이정식: 10000원",
            gu = "용산구",
            latitude = 37.5374729285246,
            longitude = 126.965664771338
        ),
        MapEntity(
            id = 2,
            name = "장수설렁탕",
            code = 1,
            codeName = "한식",
            address = "서울특별시 송파구 백제고분로 148",
            phone = "02-415-1472",
            menu = "불고기(200g호주산): 12000원, 설렁탕: 6000원",
            gu = "송파구",
            latitude = 37.5054870414361,
            longitude = 127.083121066144
        ),
        MapEntity(
            id = 3,
            name = "감베로니",
            code = 3,
            codeName = "경양식/일식",
            address = "서울특별시 용산구 한강대로 81길 5",
            phone = "02-3273-1791",
            menu = "파스타: 8000원, 돈까스: 9000원",
            gu = "용산구",
            latitude = 37.5429793370245,
            longitude = 126.971946055378
        ),
        MapEntity(
            id = 4,
            name = "남영해물탕",
            code = 1,
            codeName = "한식",
            address = "서울특별시 용산구 한강대로 80길 12",
            phone = "798-2545",
            menu = "불고기백반: 5000원, 알탕: 6000원",
            gu = "용산구",
            latitude = 37.5425431587763,
            longitude = 126.973487034748
        ),
        MapEntity(
            id = 5,
            name = "라푸스코 마포점",
            code = 4,
            codeName = "기타외식업",
            address = "서울특별시 마포구 만리재옛길 18",
            phone = "02-702-4333",
            menu = "콤비네이션: 16900원, 피자(불고기): 15900원",
            gu = "마포구",
            latitude = 37.5456041859389,
            longitude = 126.955194223342
        ),
        MapEntity(
            id = 6,
            name = "목우촌부추삼겹살",
            code = 1,
            codeName = "한식",
            address = "서울특별시 관악구 남현1길 68-10",
            phone = "02-588-7399",
            menu = "냉면(물): 5000원, 삼겹살: 13000원, 생삼겹살: 12000원, 냉동삼겹살: 8000원",
            gu = "관악구",
            latitude = 37.475339578085,
            longitude = 126.981089942431
        ),
        MapEntity(
            id = 7,
            name = "봉주르",
            code = 1,
            codeName = "한식",
            address = "서울특별시 마포구 와우산로 155-1",
            phone = "02-332-8373",
            menu = "백반정식: 5500원, 조리라면: 3500원",
            gu = "마포구",
            latitude = 37.5549789202685,
            longitude = 126.930036332426
        ),
        MapEntity(
            id = 9,
            name = "맛집2",
            code = 2,
            codeName = "중식",
            address = "서울시 관악구",
            phone = "010-1234-5678",
            menu = "짜장면",
            gu = "관악구",
            latitude = 37.474629606862,
            longitude = 126.952599252174
        ),
        MapEntity(
            id = 10,
            name = "맛집4",
            code = 4,
            codeName = "양식",
            address = "서울시 강서구",
            phone = "010-1234-5678",
            menu = "스테이크",
            gu = "강서구",
            latitude = 37.555567479084,
            longitude = 126.854371723846
        ),
        MapEntity(
            id = 11,
            name = "맛집4",
            code = 4,
            codeName = "양식",
            address = "서울시 강서구",
            phone = "010-1234-5678",
            menu = "스테이크",
            gu = "강서구",
            latitude = 37.555567479085,
            longitude = 126.854371723847
        ),
        MapEntity(
            id = 12,
            name = "맛집5",
            code = 5,
            codeName = "분식",
            address = "서울시 송파구",
            phone = "010-1234-5678",
            menu = "떡볶이",
            gu = "송파구",
            latitude = 37.5054870414361,
            longitude = 127.083121066144,
        ),
    )
}