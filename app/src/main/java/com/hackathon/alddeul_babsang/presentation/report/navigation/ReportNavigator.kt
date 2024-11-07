package com.hackathon.alddeul_babsang.presentation.report.navigation

import androidx.navigation.NavController

class ReportNavigator(
    val navController: NavController
) {
    fun navigateReportWrite() {
        navController.navigate("reportWrite")
    }
    fun navigateDetail(id: Long) {
        navController.navigate("detail?id=$id")
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}