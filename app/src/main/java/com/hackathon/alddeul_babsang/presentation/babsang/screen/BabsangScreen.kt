package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator
import com.hackathon.alddeul_babsang.util.UiState

@Composable
fun BabsangRoute(
    navigator: BabsangNavigator
) {
    val babsangViewModel: BabsangViewModel = hiltViewModel()
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        babsangViewModel.getBabsang()
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    BabsangScreen(
        onItemClick = { id -> navigator.navigateDetail(id) },
        babsangViewModel = babsangViewModel,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabsangScreen(
    onItemClick: (Long) -> Unit = {},
    babsangViewModel: BabsangViewModel,
) {
    val getBabsangState by babsangViewModel.getBabsangState.collectAsStateWithLifecycle(UiState.Empty)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(White),
                title = {
                    Text(
                        text = stringResource(R.string.tv_babsang_title),
                        style = head4Bold,
                        color = Gray900
                    )
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
                .background(color = White)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(bottom = 15.dp),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Orange800)) {
                            append(stringResource(R.string.tv_babsang_recommend1))
                        }
                        append(stringResource(R.string.tv_babsang_recommend2))
                        withStyle(style = SpanStyle(color = Orange800)) {
                            append(stringResource(R.string.tv_babsang_recommend3))
                        }
                        append(stringResource(R.string.tv_babsang_recommend4))
                    },
                    style = head6Semi
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(babsangViewModel.mockBabsangRecommendList) { item ->
                        BabsangRecommendItem(
                            onClick = { onItemClick(item.id) },
                            data = item
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom = 15.dp),
                    text = stringResource(R.string.tv_babsang_list),
                    style = head6Semi
                )
            }
            when (getBabsangState) {
                is UiState.Loading -> {
                    item {
                        LoadingCircleIndicator()
                    }
                }

                is UiState.Success -> {
                    val data = (getBabsangState as UiState.Success).data
                    if (data.isEmpty()) {
                        item {
                            Text(
                                text = "밥상 데이터가 없어요",
                                style = head6Semi,
                                color = Orange900,
                                modifier = Modifier.padding(vertical = 20.dp)
                            )
                        }
                    } else {
                        itemsIndexed(data) { index, item ->
                            BabsangItem(
                                onClick = { onItemClick(item.id) },
                                data = item
                            )
                            if (index != data.size - 1) {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }

                is UiState.Failure -> {
                    item {
                        Text(
                            text = (getBabsangState as UiState.Failure).msg,
                            style = head6Semi,
                            color = Orange900,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                }

                else -> {}
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BabsangScreenPreview() {
    AlddeulBabsangTheme {
        BabsangScreen(
            babsangViewModel = hiltViewModel()
        )
    }
}
