package com.hackathon.alddeul_babsang.presentation.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.alddeul_babsang.presentation.example.screen.ExampleRoute

fun NavGraphBuilder.exampleNavGraph(
    navigator: ExampleNavigator
) {
    composable(route = "example") {
        ExampleRoute(navigator = navigator)
    }
}