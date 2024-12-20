package com.hackathon.alddeul_babsang.presentation.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.BottomNavigationItem
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B02
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B04
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray500
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator
import com.hackathon.alddeul_babsang.presentation.babsang.screen.BabsangRoute
import com.hackathon.alddeul_babsang.presentation.main.navigation.MainNavigator
import com.hackathon.alddeul_babsang.presentation.map.navigation.MapNavigator
import com.hackathon.alddeul_babsang.presentation.map.screen.MapRoute
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import com.hackathon.alddeul_babsang.presentation.profile.screen.ProfileRoute
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator
import com.hackathon.alddeul_babsang.presentation.report.screen.ReportRoute

@Composable
fun MainRoute(
    navigator: MainNavigator,
) {
    MainScreen(
        navController = navigator.navController,
    )
}

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavigationItem(
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_map_selected),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_map_unselected),
            label = stringResource(R.string.bnv_map_label)
        ),
        BottomNavigationItem(
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_babsang_selected),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_babsang_unselected),
            label = stringResource(R.string.bnv_babsang_label)
        ),
        BottomNavigationItem(
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_report_selected),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_report_unselected),
            label = stringResource(R.string.bnv_report_label)
        ),
        BottomNavigationItem(
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile_selected),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile_unselected),
            label = stringResource(R.string.bnv_profile_label)
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.background(White),
                containerColor = White
            ) {
                items.forEachIndexed { index, item ->
                    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (selectedItem == index) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    style = body4Regular
                                )
                            },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = Font_B02,
                                unselectedTextColor = Font_B04,
                                indicatorColor = White
                            ),
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> {
                    MapRoute(navigator = MapNavigator(navController = navController))
                }
                1 -> {
                    BabsangRoute(navigator = BabsangNavigator(navController = navController))
                }
                2 -> {
                    ReportRoute(navigator = ReportNavigator(navController = navController))
                }
                3 -> {
                    ProfileRoute(navigator = ProfileNavigator(navController = navController))
                }
            }
        }

    }
}

// 눌러질때의 ripple 제거
private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AlddeulBabsangTheme {
        MainScreen(
            navController = rememberNavController()
        )
    }
}