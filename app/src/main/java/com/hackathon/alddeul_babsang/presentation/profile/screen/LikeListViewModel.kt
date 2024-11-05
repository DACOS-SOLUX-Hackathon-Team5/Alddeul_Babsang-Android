package com.hackathon.alddeul_babsang.presentation.profile.screen


import androidx.lifecycle.ViewModel
import com.hackathon.alddeul_babsang.domain.entity.BabsangDetailEntity
import com.hackathon.alddeul_babsang.domain.entity.MenuEntity
import com.hackathon.alddeul_babsang.domain.entity.ReviewEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikeListViewModel @Inject constructor() : ViewModel() {
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

    val mockLikeList = listOf(
        BabsangDetailEntity(
            id = 1,
            name = "송이네 밥상",
            codeName = "경양식/일식",
            address = "서울특별시 용산구 청파동 1211",
            phone = "02-295-0220",
            rating = 4.3,
            menu = "김치찌개: 8000, 된장찌개 9000",
            review = mockReviews
        ),
        BabsangDetailEntity(
            id = 2,
            name = "송이네 밥상2",
            codeName = "한식",
            address = "서울특별시 용산구 청파동 911",
            phone = "02-230-0220",
            rating = 4.3,
            menu = "김치찌개: 8000, 된장찌개 9000",
            review = mockReviews
        ),
        BabsangDetailEntity(
            id = 3,
            name = "송이네 밥상3",
            codeName = "중식",
            address = "서울특별시 용산구 청파동 321",
            phone = "02-210-2097",
            rating = 4.3,
            menu = "김치찌개: 8000, 된장찌개 9000",
            review = mockReviews
        ),
        BabsangDetailEntity(
            id = 4,
            name = "송이네 밥상4",
            codeName = "기타외식업",
            address = "서울특별시 용산구 청파동 14",
            phone = "02-210-0270",
            rating = 4.3,
            menu = "김치찌개: 8000, 된장찌개 9000",
            review = mockReviews
        )
    )
}