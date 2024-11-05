package com.hackathon.alddeul_babsang.presentation.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.babsangNavGraph
import com.hackathon.alddeul_babsang.presentation.detail.navigation.DetailNavigator
import com.hackathon.alddeul_babsang.presentation.detail.navigation.detailNavGraph
import com.hackathon.alddeul_babsang.presentation.detail.navigation.reviewNavGraph
import com.hackathon.alddeul_babsang.presentation.example.navigation.ExampleNavigator
import com.hackathon.alddeul_babsang.presentation.example.navigation.exampleNavGraph
import com.hackathon.alddeul_babsang.presentation.main.navigation.MainNavigator
import com.hackathon.alddeul_babsang.presentation.main.navigation.mainNavGraph
import com.hackathon.alddeul_babsang.presentation.map.navigation.MapNavigator
import com.hackathon.alddeul_babsang.presentation.map.navigation.mapNavGraph
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import com.hackathon.alddeul_babsang.presentation.profile.navigation.profileNavGraph
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator
import com.hackathon.alddeul_babsang.presentation.report.navigation.reportNavGraph
import com.hackathon.alddeul_babsang.presentation.report.navigation.reportWriteNavGraph

@Composable
fun AlddeulNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    exampleNavigator: ExampleNavigator,
    mainNavigator: MainNavigator,
    babsangNavigator: BabsangNavigator,
    mapNavigator: MapNavigator,
    profileNavigator: ProfileNavigator,
    reportNavigator: ReportNavigator,
    detailNavigator: DetailNavigator
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = "main",
        ) {
            exampleNavGraph(exampleNavigator)
            mainNavGraph(
                mainNavigator,
                babsangNavigator,
                mapNavigator,
                profileNavigator,
                reportNavigator
            )
            babsangNavGraph(babsangNavigator)
            mapNavGraph(mapNavigator)
            profileNavGraph(profileNavigator)
            reportNavGraph(reportNavigator)
            detailNavGraph(detailNavigator)
            reviewNavGraph(detailNavigator)
            reportWriteNavGraph(reportNavigator)
        }
    }
}