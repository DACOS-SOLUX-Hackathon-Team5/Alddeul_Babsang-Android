package com.hackathon.alddeul_babsang.presentation.report.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

@Composable
fun ReportRoute(
    navigator: ReportNavigator
) {
    ReportScreen(
        onReportWriteClick = {
            navigator.navigateReportWrite()
        }
    )
}

@Composable
fun ReportScreen(
    onReportWriteClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "제보"
        )
        Button(onClick = { onReportWriteClick() }) {
            Text(text = "제보하러 가기")
        }
    }
}