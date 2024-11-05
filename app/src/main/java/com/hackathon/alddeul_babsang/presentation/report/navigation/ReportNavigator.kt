package com.hackathon.alddeul_babsang.presentation.report.navigation

import androidx.navigation.NavController

class ReportNavigator(
    val navController: NavController
) {
    fun navigateReportWrite() {
        navController.navigate("reportWrite")
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}