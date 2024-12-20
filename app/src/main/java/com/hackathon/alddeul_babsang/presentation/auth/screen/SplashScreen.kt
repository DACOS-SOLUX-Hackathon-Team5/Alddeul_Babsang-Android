package com.hackathon.alddeul_babsang.presentation.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.Peach200
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.bmDohyeonRegular
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(navController: NavController, modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()
    val verticalGradient = Brush.verticalGradient(
        colors = listOf(Orange900, Orange900, Peach200),
        startY = 0.0f,
        endY = 1500f,
    )
    val loginViewModel: LoginViewModel = hiltViewModel()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Orange900,
        )
    }

    LaunchedEffect(Unit) {
        delay(2500)
        when {
            (loginViewModel.getUserAccessToken().toString().isNotBlank() &&
                    loginViewModel.getCheckLogin().first()) -> {
                navController.navigate("main") {
                    popUpTo("splash") {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            else -> {
                navController.navigate("login") {
                    popUpTo("splash") {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(verticalGradient)
                .zIndex(1f)
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .zIndex(2f),
            text = stringResource(R.string.tv_splash_title),
            color = White,
            style = bmDohyeonRegular,
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .zIndex(0f),
            painter = painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AlddeulBabsangTheme {
        SplashScreen(navController = rememberNavController())
    }
}