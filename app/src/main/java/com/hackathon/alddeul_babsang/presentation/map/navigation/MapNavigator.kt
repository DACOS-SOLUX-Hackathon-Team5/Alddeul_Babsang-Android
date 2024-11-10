package com.hackathon.alddeul_babsang.presentation.map.navigation

import androidx.navigation.NavController

class MapNavigator(
    val navController: NavController
) {
    fun navigateDetail(id: Long) {
        val route = "detail?id=${id}"
        navController.navigate(route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}