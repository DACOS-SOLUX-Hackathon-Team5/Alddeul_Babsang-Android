package com.hackathon.alddeul_babsang.presentation.map.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.map.screen.MapRoute

fun NavGraphBuilder.mapNavGraph(
    navigator: MapNavigator
) {
    composable(route = "map") {
        MapRoute(navigator = navigator)
    }
}