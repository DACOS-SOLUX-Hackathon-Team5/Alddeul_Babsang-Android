package com.hackathon.alddeul_babsang.presentation.report.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

@Composable
fun ReportRoute(
    navigator: ReportNavigator
) {
    ReportScreen()
}

@Composable
fun ReportScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "제보")
    }
}