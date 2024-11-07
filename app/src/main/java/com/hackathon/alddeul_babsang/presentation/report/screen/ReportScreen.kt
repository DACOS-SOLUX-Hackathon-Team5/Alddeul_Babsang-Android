package com.hackathon.alddeul_babsang.presentation.report.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.head6Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head7Bold
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

@Composable
fun ReportRoute(
    navigator: ReportNavigator
) {

    val babsangListViewModel: ReportBabsangViewModel = hiltViewModel()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    ReportScreen(
        onItemClick = { id -> navigator.navigateDetail(id) },
        onReportWriteClick = {
            navigator.navigateReportWrite()
        },

        babsangListViewModel = babsangListViewModel
    )

}

@Composable
fun ReportScreen(
    onItemClick: (Long) -> Unit = {},
    onReportWriteClick: () -> Unit = {},
    babsangListViewModel: ReportBabsangViewModel
) {

    Box {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),

            modifier = Modifier
                .fillMaxSize()
                .background(color = White)
                .padding(horizontal = 20.dp, vertical = 25.dp)
        ) {

            item {
                Text(stringResource(R.string.tv_report_title1), style = head7Bold, color = Gray900)
                Text(
                    stringResource(R.string.tv_report_title2),
                    style = head6Bold,
                    color = Orange900
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(stringResource(R.string.tv_report_title3), style = head7Bold, color = Gray900)

                Spacer(modifier = Modifier.height(14.dp))
            }

            items(babsangListViewModel.mockReportBabsang) { item ->
                BabsangItem(
                    onClick = { onItemClick(item.id) },
                    data = item
                )
            }
        }

        Button(
            onClick = { onReportWriteClick() },
            modifier = Modifier
                .width(170.dp)
                .height(70.dp)
                .offset(y = -20.dp, x = -10.dp)
                .align(Alignment.BottomEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue, // 버튼 배경색
                contentColor = White // 버튼 텍스트 색상
            )
        ) {
            Text(text = "제보하러 가기", style = head7Bold) // 텍스트 색상은 흰색으로 설정
        }
    }
}


@Preview
@Composable
fun ReportScreenPreview() {
    val babsangListViewModel: ReportBabsangViewModel = hiltViewModel()
    AlddeulBabsangTheme {
        ReportScreen(
            onReportWriteClick = {
            },
            babsangListViewModel = babsangListViewModel
        )

    }
}