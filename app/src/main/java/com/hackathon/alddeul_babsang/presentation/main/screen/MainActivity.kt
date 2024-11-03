package com.hackathon.alddeul_babsang.presentation.main.screen

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
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
        enableEdgeToEdge()
        setContent {
            AlddeulBabsangTheme {
                val context = LocalContext.current
                var backPressedState by remember { mutableStateOf(true) }
                var backPressedTime = 0L

                BackHandler(enabled = backPressedState) {
                    if (System.currentTimeMillis() - backPressedTime <= 3000) {
                        (context as Activity).finish()
                    } else {
                        backPressedState = true
                        context.toast("한번 더 누르면 종료돼요")
                    }
                    backPressedTime = System.currentTimeMillis()
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
                            modifier = Modifier.padding(paddingValues),
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