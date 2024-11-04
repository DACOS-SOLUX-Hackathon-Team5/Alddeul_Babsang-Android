package com.hackathon.alddeul_babsang.presentation.babsang.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.babsang.screen.BabsangRoute

fun NavGraphBuilder.babsangNavGraph(
    navigator: BabsangNavigator
) {
    composable(route = "babsang") {
        BabsangRoute(navigator = navigator)
    }
}