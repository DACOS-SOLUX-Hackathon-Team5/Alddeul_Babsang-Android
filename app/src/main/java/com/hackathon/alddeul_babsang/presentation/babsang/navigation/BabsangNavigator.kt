package com.hackathon.alddeul_babsang.presentation.babsang.navigation

import androidx.navigation.NavController

class BabsangNavigator(
    val navController: NavController
) {
    fun navigateDetail(id: Long) {
        navController.navigate("detail?id=$id")
    }
}