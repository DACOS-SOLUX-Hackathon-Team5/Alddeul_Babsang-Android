package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator

@Composable
fun ProfileRoute(
    navigator: ProfileNavigator
) {
    ProfileScreen()
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "프로필")
    }
}