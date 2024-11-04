package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.lifecycle.ViewModel
import com.hackathon.alddeul_babsang.domain.entity.MenuEntity
import com.hackathon.alddeul_babsang.domain.entity.ReviewEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
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
}