package com.hackathon.alddeul_babsang.presentation.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.profile.screen.LikeRoute
import com.hackathon.alddeul_babsang.presentation.profile.screen.ProfileRoute


fun NavGraphBuilder.profileNavGraph(
    navigator: ProfileNavigator
) {
    composable(route = "profile") {
        ProfileRoute(navigator = navigator)
    }
    composable(route = "like") {
        LikeRoute(navigator = navigator)
    }
}