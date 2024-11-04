package com.hackathon.alddeul_babsang.presentation.main.screen

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator
import com.hackathon.alddeul_babsang.presentation.example.navigation.ExampleNavigator
import com.hackathon.alddeul_babsang.presentation.main.navigation.MainNavigator
import com.hackathon.alddeul_babsang.presentation.map.navigation.MapNavigator
import com.hackathon.alddeul_babsang.presentation.navigator.AlddeulNavHost
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator
import com.hackathon.alddeul_babsang.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            AlddeulBabsangTheme {
                val context = LocalContext.current
                var backPressedState by remember { mutableStateOf(true) }
                var backPressedTime = 0L
                val systemUiController = rememberSystemUiController()
                val lifecycleOwner = LocalLifecycleOwner.current

                BackHandler(enabled = backPressedState) {
                    if (System.currentTimeMillis() - backPressedTime <= 3000) {
                        (context as Activity).finish()
                    } else {
                        backPressedState = true
                        context.toast(getString(R.string.toast_back_handler))
                    }
                    backPressedTime = System.currentTimeMillis()
                }

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = White
                    )
                }

                DisposableEffect(key1 = lifecycleOwner) {
                    onDispose {
                        systemUiController.setStatusBarColor(
                            color = Transparent
                        )
                    }
                }

                val navController = rememberNavController()
                val exampleNavigator = remember(navController) { ExampleNavigator(navController) }
                val mainNavigator = remember(navController) { MainNavigator(navController) }
                val mapNavigator = remember(navController) { MapNavigator(navController) }
                val babsangNavigator = remember(navController) { BabsangNavigator(navController) }
                val reportNavigator = remember(navController) { ReportNavigator(navController) }
                val profileNavigator = remember(navController) { ProfileNavigator(navController) }

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    content = { paddingValues ->
                        AlddeulNavHost(
                            modifier = Modifier
                                .padding(paddingValues),
                            navController = navController,
                            exampleNavigator = exampleNavigator,
                            mainNavigator = mainNavigator,
                            mapNavigator = mapNavigator,
                            babsangNavigator = babsangNavigator,
                            reportNavigator = reportNavigator,
                            profileNavigator = profileNavigator
                        )
                    }
                )
            }
        }
    }
}