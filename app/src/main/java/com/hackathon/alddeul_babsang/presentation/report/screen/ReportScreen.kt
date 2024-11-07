package com.hackathon.alddeul_babsang.presentation.report.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.*
import com.hackathon.alddeul_babsang.domain.entity.BabsangListEntity
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import com.hackathon.alddeul_babsang.presentation.profile.screen.BabsangListViewModel
import com.hackathon.alddeul_babsang.presentation.profile.screen.LikeItem
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

@Composable
fun ReportRoute(
    navigator: ReportNavigator
) {

    val babsangListViewModel: BabsangListViewModel = hiltViewModel()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    ReportScreen(
        onReportWriteClick = {
            navigator.navigateReportWrite()
        },
        navigator = navigator,  // navController 대신 navigator 전달
        babsangListViewModel = babsangListViewModel
    )

}

@Composable
fun ReportScreen(
    onReportWriteClick: () -> Unit = {},
    navigator: ReportNavigator,
    babsangListViewModel: BabsangListViewModel
) {


    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(horizontal = 20.dp, vertical = 25.dp)
            .verticalScroll(scrollState),

        ) {
        Text("직접 제보받은", style = head7Bold, color = Gray900)
        Text("착한 밥상 후보 리스트", style = head6Bold, color = Orange900)
        Spacer(modifier = Modifier.height(10.dp))
        Text("제보와 후기로 쌓아 보아요", style = head7Bold, color = Gray900)

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (item in babsangListViewModel.mockLikeList) {
                BabsangItem(
                    navigator = navigator,
                    data = item
                )
            }
        }








        Button(onClick = { onReportWriteClick() }) {
            Text(text = "제보하러 가기")
        }
    }
}


@Preview
@Composable
fun ReportScreenPreview() {
    val babsangListViewModel: BabsangListViewModel = hiltViewModel()
    val navController = rememberNavController()
    val navigator = ReportNavigator(navController)
    AlddeulBabsangTheme {
        ReportScreen(
            onReportWriteClick = {
            },
            navigator = navigator,
            babsangListViewModel = babsangListViewModel
        )

    }
}