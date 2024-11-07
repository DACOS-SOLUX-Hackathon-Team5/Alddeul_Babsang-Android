package com.hackathon.alddeul_babsang.presentation.report.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import com.hackathon.alddeul_babsang.core_ui.theme.title4Bold
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

@Composable
fun ReportRoute(
    navigator: ReportNavigator
) {

    val babsangListViewModel: ReportViewModel = hiltViewModel()
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
    babsangListViewModel: ReportViewModel
) {
    Scaffold(
        floatingActionButton = {
            Button(
                shape = RoundedCornerShape(40.dp),
                onClick = { onReportWriteClick() },
                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 41.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                )
            ) {
                Text(
                    text = stringResource(R.string.btn_report),
                    style = title4Bold,
                    color = White
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),

            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = White)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(top = 25.dp),
                    text = stringResource(R.string.tv_report_title1),
                    style = head7Bold,
                    color = Gray900
                )
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = stringResource(R.string.tv_report_title2),
                    style = head6Bold,
                    color = Orange900
                )
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = stringResource(R.string.tv_report_title3),
                    style = head7Bold, color = Gray900
                )
            }
            items(babsangListViewModel.mockReportBabsang) { item ->
                ReportItem(
                    onClick = { onItemClick(item.id) },
                    data = item
                )
            }
        }
    }
}


@Preview
@Composable
fun ReportScreenPreview() {
    val babsangListViewModel: ReportViewModel = hiltViewModel()
    AlddeulBabsangTheme {
        ReportScreen(
            onReportWriteClick = {
            },
            babsangListViewModel = babsangListViewModel
        )

    }
}