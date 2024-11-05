package com.hackathon.alddeul_babsang.presentation.auth.navigation

import androidx.navigation.NavHostController

class AuthNavigator(
    val navController: NavHostController
) {
    fun navigateLogin() {
        navController.navigate(route = "login")
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateMain() {
        navController.navigate(route = "main") {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    fun navigateSignUp1() {
        navController.navigate(route = "signup1")
    }

    fun navigateSignUp2() {
        navController.navigate(route = "signup2")
    }
}