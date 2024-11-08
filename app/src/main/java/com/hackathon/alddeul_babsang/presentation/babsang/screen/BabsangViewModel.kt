package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.lifecycle.ViewModel
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity
import com.hackathon.alddeul_babsang.domain.entity.ReportEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BabsangViewModel @Inject constructor() : ViewModel() {
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