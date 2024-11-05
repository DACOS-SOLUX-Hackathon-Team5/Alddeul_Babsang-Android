package com.hackathon.alddeul_babsang.presentation.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.auth.screen.LoginRoute

fun NavGraphBuilder.loginNavGraph(
    navigator: AuthNavigator
) {
    composable(route = "login") {
        LoginRoute(navigator)
    }
}