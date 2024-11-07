package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.lifecycle.ViewModel
import com.hackathon.alddeul_babsang.domain.entity.BabsangListEntity
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BabsangRecommendViewModel @Inject constructor() : ViewModel() {


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
        ),


        )
}