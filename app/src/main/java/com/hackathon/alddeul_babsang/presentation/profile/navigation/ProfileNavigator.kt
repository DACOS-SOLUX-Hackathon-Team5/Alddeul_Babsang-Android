package com.hackathon.alddeul_babsang.presentation.profile.navigation

import androidx.navigation.NavController

class ProfileNavigator(
    val navController: NavController
) {

    fun navigateDetail(id: Long) {
        navController.navigate("detail?id=$id")
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateLogin() {
        navController.navigate("login") {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }

    }

    fun navigateLike() {
        navController.navigate("like")
    }

}