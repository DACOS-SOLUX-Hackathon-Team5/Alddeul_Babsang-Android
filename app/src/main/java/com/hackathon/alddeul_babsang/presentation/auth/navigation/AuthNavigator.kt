package com.hackathon.alddeul_babsang.presentation.auth.navigation

import androidx.navigation.NavHostController

class AuthNavigator(
    val navController: NavHostController
) {
    fun navigateLogin() {
        navController.navigate(route = "login")
    }

    fun navigateMain() {
        navController.navigate(route = "main") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    fun navigateSignUp() {
        navController.navigate(route = "signup")
    }
}