package com.hackathon.alddeul_babsang.presentation.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator
import com.hackathon.alddeul_babsang.presentation.main.screen.MainRoute
import com.hackathon.alddeul_babsang.presentation.map.navigation.MapNavigator
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

fun NavGraphBuilder.mainNavGraph(
    mainNavigator: MainNavigator,
    babsangNavigator: BabsangNavigator,
    mapNavigator: MapNavigator,
    profileNavigator: ProfileNavigator,
    reportNavigator: ReportNavigator,
) {
    composable(route = "main") {
        MainRoute(navigator = mainNavigator)
    }
}