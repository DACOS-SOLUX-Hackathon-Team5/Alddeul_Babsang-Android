package com.hackathon.alddeul_babsang.presentation.report.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.report.screen.ReportRoute

fun NavGraphBuilder.reportNavGraph(
    navigator: ReportNavigator
) {
    composable(route = "report") {
        ReportRoute(navigator = navigator)
    }
}