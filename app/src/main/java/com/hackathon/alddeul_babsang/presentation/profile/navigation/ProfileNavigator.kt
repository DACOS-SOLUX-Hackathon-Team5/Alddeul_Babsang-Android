package com.hackathon.alddeul_babsang.presentation.profile.navigation

import androidx.navigation.NavController

class ProfileNavigator(
    val navController: NavController
) {

    fun profileLikeList() {
        navController.navigate("profileLikeList")
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}