package com.hackathon.alddeul_babsang.presentation.detail.navigation

import androidx.navigation.NavController

class DetailNavigator(
    val navController: NavController
) {
    fun navigateBack() {
        navController.popBackStack()
    }
}