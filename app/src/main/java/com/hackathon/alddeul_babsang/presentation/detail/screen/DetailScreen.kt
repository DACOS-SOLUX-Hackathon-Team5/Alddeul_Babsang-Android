package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hackathon.alddeul_babsang.presentation.detail.navigation.DetailNavigator

@Composable
fun DetailRoute(
    navigator: DetailNavigator,
    id: Long
) {
    DetailScreen()
}

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Detail")
    }
}