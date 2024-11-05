package com.hackathon.alddeul_babsang.presentation.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.auth.screen.LoginRoute
import com.hackathon.alddeul_babsang.presentation.auth.screen.SignUp1Route
import com.hackathon.alddeul_babsang.presentation.auth.screen.SignUp2Route

fun NavGraphBuilder.loginNavGraph(
    navigator: AuthNavigator
) {
    composable(route = "login") {
        LoginRoute(navigator)
    }
}

fun NavGraphBuilder.signUp1NavGraph(
    navigator: AuthNavigator
) {
    composable(route = "signUp1") {
        SignUp1Route(navigator)
    }
}

fun NavGraphBuilder.signUp2NavGraph(
    navigator: AuthNavigator
) {
    composable(route = "signUp2") {
        SignUp2Route(navigator)
    }
}