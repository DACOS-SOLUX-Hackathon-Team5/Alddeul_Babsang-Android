package com.hackathon.alddeul_babsang.presentation.profile.navigation

import androidx.navigation.NavController

class ProfileNavigator(
    val navController: NavController
) {

    fun navigateBack() {
        navController.popBackStack()
    }
}