package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head7Bold
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator
import com.hackathon.alddeul_babsang.presentation.profile.screen.BabsangListViewModel

@Composable
fun BabsangRoute(
    navigator: BabsangNavigator
) {
    val babsangListViewModel: BabsangListViewModel = hiltViewModel()
    val babsangRecommendViewModel: BabsangRecommendViewModel = hiltViewModel()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    BabsangScreen(
        navigator = navigator,  // navController 대신 navigator 전달
        babsangListViewModel = babsangListViewModel,
        babsangRecommendViewModel = babsangRecommendViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabsangScreen(
    navigator: BabsangNavigator,
    babsangListViewModel: BabsangListViewModel,
    babsangRecommendViewModel: BabsangRecommendViewModel
) {
    val scrollState = rememberScrollState()
    val rowScrollState = rememberScrollState()
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = White)
                .padding(horizontal = 20.dp, vertical = 25.dp)
                .verticalScroll(scrollState),

            ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Orange800)) {
                        append("알뜰 밥상")
                    }
                    append("이 ")
                    withStyle(style = SpanStyle(color = Orange800)) {
                        append("추천")
                    }
                    append("하는 밥상")
                },
                style = head6Semi
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .horizontalScroll(rowScrollState)
            ) {
                for (item in babsangRecommendViewModel.mockBabsangRecommendList) {
                    BabsangRecommendItem(
                        navigator = navigator,
                        data = item
                    )
                }
            }


            Spacer(modifier = Modifier.height(30.dp))

            Text("착한 밥상 리스트", style = head6Semi)

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (item in babsangListViewModel.mockLikeList) {
                    BabsangListItem(
                        navigator = navigator,
                        data = item
                    )
                }
            }
        }}




}

@Preview(showBackground = true)
@Composable
fun BabsangScreenPreview() {
    val babsangListViewModel: BabsangListViewModel = hiltViewModel()
    val babsangRecommendViewModel: BabsangRecommendViewModel = hiltViewModel()
    val navController = rememberNavController()
    val navigator = BabsangNavigator(navController)

    AlddeulBabsangTheme {
        BabsangScreen(
            navigator = navigator,
            babsangListViewModel = babsangListViewModel,
            babsangRecommendViewModel = babsangRecommendViewModel
        )
    }
}
