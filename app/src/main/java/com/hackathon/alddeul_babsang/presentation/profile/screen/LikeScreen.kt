package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator

@Composable
fun LikeRoute(
    navigator: ProfileNavigator
) {

    val systemUiController = rememberSystemUiController()
    val likeViewModel: LikeViewModel = hiltViewModel()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
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
    val scrollState = rememberScrollState()

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
            items(likeViewModel.mockLikes) { item ->
                LikeItem(
                    onClick = { onItemClick(item.id) },
                    data = item
                )
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