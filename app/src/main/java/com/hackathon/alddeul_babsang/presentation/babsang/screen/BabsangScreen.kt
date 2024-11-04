package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator

@Composable
fun BabsangRoute(
    navigator: BabsangNavigator
) {
    BabsangScreen()
}

@Composable
fun BabsangScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "착한 밥상")
    }
}

@Preview(showBackground = true)
@Composable
fun BabsangScreenPreview() {
    AlddeulBabsangTheme {
        BabsangScreen()
    }
}
