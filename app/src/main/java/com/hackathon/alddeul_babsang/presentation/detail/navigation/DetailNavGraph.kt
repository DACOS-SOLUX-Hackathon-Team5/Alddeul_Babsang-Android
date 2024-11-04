package com.hackathon.alddeul_babsang.presentation.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hackathon.alddeul_babsang.presentation.detail.screen.DetailRoute

fun NavGraphBuilder.detailNavGraph(
    navigator: DetailNavigator
) {
    composable(
        route = "detail?id={id}",
        arguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )
    ) { backStackEntry ->
        DetailRoute(
            navigator = navigator,
            id = backStackEntry.arguments?.getLong("id") ?: -1
        )
    }
}