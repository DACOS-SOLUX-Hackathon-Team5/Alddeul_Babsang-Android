package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.LoadingCircleIndicator
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import com.hackathon.alddeul_babsang.util.UiState
import timber.log.Timber

@Composable
fun LikeRoute(
    navigator: ProfileNavigator
) {

    val systemUiController = rememberSystemUiController()
    val likeViewModel: LikeViewModel = hiltViewModel()

    val postLikeState by likeViewModel.postLikeState.collectAsStateWithLifecycle(UiState.Empty)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    when (postLikeState) {
        is UiState.Success -> {
            navigator.navigateBack()
        }

        is UiState.Failure -> {
            Timber.e((postLikeState as UiState.Failure).msg)
        }

        else -> {}
    }

    LaunchedEffect(Unit) {
        likeViewModel.getLikes()
    }

    LikeScreen(
        onItemClick = { id -> navigator.navigateDetail(id) },
        onBackClick = { navigator.navigateBack() },
        likeViewModel = likeViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeScreen(
    onItemClick: (Long) -> Unit = {},
    onBackClick: () -> Unit = {},
    likeViewModel: LikeViewModel
) {
    val getLikesState by likeViewModel.getLikesState.collectAsStateWithLifecycle(UiState.Empty)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(White),
                title = {
                    Text(
                        text = stringResource(R.string.tv_profilelikelist_title),
                        style = head4Bold,
                        color = Gray900
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Orange800)) {
                            append(stringResource(R.string.tv_like_history))
                        }
                        append(stringResource(R.string.tv_like_connection))
                        withStyle(style = SpanStyle(color = Orange800)) {
                            append(stringResource(R.string.tv_like_recommend))
                        }
                        append(stringResource(R.string.tv_like_end))
                    },
                    style = head6Semi
                )
            }

            when (getLikesState) {
                is UiState.Success -> {
                    val data = (getLikesState as UiState.Success).data // getLikesState로 수정
                    if (data.isEmpty()) {
                        item {
                            Text(
                                text = "좋아요를 누른 밥상이 없어요",
                                style = head6Semi,
                                color = Orange900,
                                modifier = Modifier.padding(vertical = 20.dp)
                            )
                        }
                    } else {
                        itemsIndexed(data) { index, item ->
                            LikeItem(
                                onClick = { onItemClick(item.restaurantId) },
                                data = item,
                                likeViewModel = likeViewModel
                            )

                            // Spacer는 마지막 아이템을 제외하고 넣어야 하므로 조건 추가
                            if (index != data.size - 1) {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }

                is UiState.Loading -> {
                    item {
                        LoadingCircleIndicator()
                    }
                }

                is UiState.Failure -> {
                    item {
                        Text(text = (getLikesState as UiState.Failure).msg)
                    }
                }
                else -> {}

            }


        }
    }
}

@Preview
@Composable
fun ProfileLikeListScreenPreview() {
    AlddeulBabsangTheme {
        LikeScreen(
            likeViewModel = hiltViewModel()
        )
    }
}