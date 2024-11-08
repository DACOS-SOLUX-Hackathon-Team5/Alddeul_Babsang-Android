package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray500
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange400
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Red
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body4Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head7Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailDto
import com.hackathon.alddeul_babsang.presentation.detail.navigation.DetailNavigator
import com.hackathon.alddeul_babsang.presentation.profile.screen.LikeViewModel
import com.hackathon.alddeul_babsang.util.UiState
import timber.log.Timber
import kotlin.math.round

@Composable
fun DetailRoute(
    navigator: DetailNavigator,
    id: Long
) {
    val systemUiController = rememberSystemUiController()
    val detailViewModel: DetailViewModel = hiltViewModel()
    val postDetailState by detailViewModel.postDetailState.collectAsStateWithLifecycle(initialValue = UiState.Empty)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Orange700
        )
    }

    LaunchedEffect(Unit) {
        detailViewModel.getReviews(id)
        detailViewModel.postDetail(id.toInt())
    }

    when (postDetailState) {
        is UiState.Success -> {
            (postDetailState as UiState.Success).data?.let {
                DetailScreen(
                    data = it,
                    onBackClick = { navigator.navigateBack() },
                    onReviewClick = { id -> navigator.navigateReview(id) },
                    onItemClick = { id -> navigator.navigateDetail(id) },
                    detailViewModel = detailViewModel
                )
            }
            Timber.d("Post detail success: ${(postDetailState as UiState.Success).data}")

        }

        is UiState.Failure -> {
            Timber.e("Post detail failed: ${(postDetailState as UiState.Failure).msg}")
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    data: ResponseDetailDto,
    onBackClick: () -> Unit = {},
    onReviewClick: (Long) -> Unit = {},
    onItemClick: (Long) -> Unit = {},
    detailViewModel: DetailViewModel
) {
    var isFavorite by remember { mutableStateOf(data.storeInfo.favorite) }
    val getReviewsState by detailViewModel.getReviewsState.collectAsStateWithLifecycle(UiState.Empty)
    val likeViewModel: LikeViewModel = hiltViewModel()

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
                    IconButton(onClick = {
                        isFavorite = !isFavorite
                        likeViewModel.postLike(storeId = data.storeInfo.storeId)
                    }
                    ) {
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Orange700)
                        .padding(horizontal = 20.dp)
                ) {
                    AsyncImage(
                        model = data.storeInfo.imageUrl ?: when (data.storeInfo.category) {
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
                                if (data.storeInfo.imageUrl == null) Modifier.size(100.dp) else Modifier
                            ),
                        contentScale = if (data.storeInfo.imageUrl == null) ContentScale.None else ContentScale.FillBounds,
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
                            text = data.storeInfo.name,
                            style = head4Bold,
                            color = Gray900
                        )
                        Text(
                            modifier = Modifier.padding(top = 15.dp),
                            text = data.storeInfo.address,
                            style = head7Regular,
                            color = Gray300
                        )
                        Text(
                            text = data.storeInfo.contact.ifBlank { "연락처 정보 없음" },
                            style = head7Regular,
                            color = Gray300
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Image(
                            modifier = Modifier.size(width = 185.dp, height = 35.dp),
                            painter = when (round(data.aveRating)) {
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
                            onClick = { onReviewClick(data.storeInfo.storeId) },
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
                }
            }
            when (getReviewsState) {
                is UiState.Success -> {
                    val data = (getReviewsState as UiState.Success).data
                    item {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                            text = stringResource(
                                R.string.tv_detail_review,
                                data.size
                            ),
                            style = head7Semi,
                            color = Gray900
                        )
                    }
                    itemsIndexed(data) { index, item ->
                        ReviewItem(
                            data = item
                        )
                        if (index != data.size - 1) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }

                is UiState.Failure -> {
                    item {
                        Text(
                            text = (getReviewsState as UiState.Failure).msg,
                            style = head7Semi,
                            color = Orange700,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                    Timber.e("Get reviews failed: ${(getReviewsState as UiState.Failure).msg}")
                }

                else -> {}
            }
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    text = stringResource(id = R.string.tv_detail_recommend),
                    style = head7Semi,
                    color = Gray900
                )
            }
            item {
                LazyVerticalGrid(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(detailViewModel.mockDetailRecommend) { item ->
                        DetailRecommendedItem(
                            data = item,
                            onClick = { onItemClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}