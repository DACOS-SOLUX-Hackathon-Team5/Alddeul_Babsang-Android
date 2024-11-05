package com.hackathon.alddeul_babsang.presentation.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.profile.screen.ProfileLikeListRoute
import com.hackathon.alddeul_babsang.presentation.profile.screen.ProfileRoute
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator
import com.hackathon.alddeul_babsang.presentation.report.screen.ReportWriteRoute

fun NavGraphBuilder.profileNavGraph(
    navigator: ProfileNavigator
) {
    composable(route = "profile") {
        ProfileRoute(navigator = navigator)
    }
}

fun NavGraphBuilder.profileLikeListNavGraph(
    navigator: ProfileNavigator
) {
    composable(route = "profileLikeList") {
        ProfileLikeListRoute(navigator = navigator)
    }
}