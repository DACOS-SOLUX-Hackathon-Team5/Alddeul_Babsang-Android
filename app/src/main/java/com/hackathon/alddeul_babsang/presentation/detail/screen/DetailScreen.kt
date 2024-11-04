package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange400
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Red
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body4Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head7Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi
import com.hackathon.alddeul_babsang.domain.entity.BabsangDetailEntity
import com.hackathon.alddeul_babsang.domain.entity.ReviewEntity
import com.hackathon.alddeul_babsang.presentation.detail.navigation.DetailNavigator
import kotlin.math.round

@Composable
fun DetailRoute(
    navigator: DetailNavigator,
    id: Long
) {
    val systemUiController = rememberSystemUiController()
    val lifecycleOwner = LocalLifecycleOwner.current
    val detailViewModel: DetailViewModel = hiltViewModel()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Orange700
        )
    }

    DisposableEffect(key1 = lifecycleOwner) {
        onDispose {
            systemUiController.setStatusBarColor(
                color = White
            )
        }
    }

    DetailScreen(
        data = BabsangDetailEntity(
            id = 1,
            name = "송이네 밥상",
            codeName = "경양식/일식",
            address = "서울특별시 용산구 청파동 11",
            phone = "02-210-0220",
            rating = 4.3,
            menu = "김치찌개: 8000, 된장찌개 9000",
            review = detailViewModel.mockReviews
        ),
        onBackClick = { navigator.navigateBack() },
        onReviewClick = { id -> navigator.navigateReview(id) },
        detailViewModel = detailViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    data: BabsangDetailEntity,
    onBackClick: () -> Unit = {},
    onReviewClick: (Long) -> Unit = {},
    detailViewModel: DetailViewModel
) {
    var isFavorite by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .background(Orange700),
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_white),
                            contentDescription = null,
                            tint = White
                        )
                    }
                },
                title = {},
                actions = {
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite)
                                ImageVector.vectorResource(
                                    id = R.drawable.ic_heart_red
                                )
                            else ImageVector.vectorResource(
                                id = R.drawable.ic_heart_white
                            ),
                            contentDescription = null,
                            tint = if (isFavorite) Red else White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Orange700
                ),
            )
        },
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Orange700)
                    .padding(horizontal = 20.dp)
            ) {
                AsyncImage(
                    model = data.avatar ?: when (data.codeName) {
                        "한식" -> R.drawable.ic_korean_food
                        "중식" -> R.drawable.ic_chinese_food
                        "경양식/일식" -> R.drawable.ic_japanese_food
                        else -> R.drawable.ic_etc_food
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                        .height(200.dp)
                        .background(Orange400)
                        .then(
                            if (data.avatar == null) Modifier.size(100.dp) else Modifier
                        ),
                    contentScale = if (data.avatar == null) ContentScale.None else ContentScale.FillBounds,
                    alignment = Alignment.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = White,
                            shape = RoundedCornerShape(
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            ),
                        )
                        .border(
                            width = 1.dp,
                            color = Orange700,
                            shape = RoundedCornerShape(
                                bottomStart = 10.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = data.name,
                        style = head4Bold,
                        color = Gray900
                    )
                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        text = data.address,
                        style = head7Regular,
                        color = Gray300
                    )
                    Text(
                        text = data.phone,
                        style = head7Regular,
                        color = Gray300
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Image(
                        modifier = Modifier.size(width = 185.dp, height = 35.dp),
                        painter = when (round(data.rating)) {
                            in 0.0..1.4 -> painterResource(id = R.drawable.ic_star_one)
                            in 1.5..2.4 -> painterResource(id = R.drawable.ic_star_two)
                            in 2.5..3.4 -> painterResource(id = R.drawable.ic_star_three)
                            in 3.5..4.4 -> painterResource(id = R.drawable.ic_star_four)
                            in 4.5..5.0 -> painterResource(id = R.drawable.ic_star_five)
                            else -> throw IllegalArgumentException("Invalid star value")
                        },
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { onReviewClick(data.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.btn_detail_review),
                            style = body4Semi,
                            color = White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = stringResource(R.string.tv_detail_menu),
                    style = head7Semi,
                    color = Gray900
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Gray200,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 19.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    for (item in detailViewModel.mockMenuList) {
                        MenuItem(
                            data = item
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = stringResource(
                        R.string.tv_detail_review,
                        detailViewModel.mockReviews.size
                    ),
                    style = head7Semi,
                    color = Gray900
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    for (item in detailViewModel.mockReviews) {
                        ReviewItem(
                            data = item
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AlddeulBabsangTheme {
        DetailScreen(
            data = BabsangDetailEntity(
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
                )
            ),
            detailViewModel = hiltViewModel()
        )
    }
}