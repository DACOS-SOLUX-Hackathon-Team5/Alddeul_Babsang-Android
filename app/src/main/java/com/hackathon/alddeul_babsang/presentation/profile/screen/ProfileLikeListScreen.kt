package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.domain.entity.BabsangListEntity
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator

@Composable
fun ProfileLikeListRoute(
    navigator: ProfileNavigator
) {

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    val babsangListViewModel: BabsangListViewModel = hiltViewModel()
    ProfileLikeListScreen(
        navigator = navigator,  // navController 대신 navigator 전달
        data = BabsangListEntity(
            id = 1,
            avatar = "",
            name = "송이네 밥상",
            codeName = "경양식/일식",
            address = "서울특별시 용산구 청파동 11",
            phone = "02-210-0220",
            favorite = true
        ),
        onBackClick = { navigator.navigateBack() },
        babsangListViewModel = babsangListViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileLikeListScreen(
    navigator: ProfileNavigator,
    data: BabsangListEntity,
    onBackClick: () -> Unit = {},
    babsangListViewModel: BabsangListViewModel
) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(White),
                title = {
                    Text(
                        text = stringResource(R.string.tv_profilelikelist_title),
                        style = head4Bold,
                        color = Gray900
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .verticalScroll(scrollState),
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Orange800)) {
                        append("좋아요 히스토리")
                    }
                    append("를 바탕으로\n")
                    withStyle(style = SpanStyle(color = Orange800)) {
                        append("밥상을 추천")
                    }
                    append("해드려요")
                },
                style = head6Semi

            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (item in babsangListViewModel.mockLikeList) {
                    LikeItem(
                        navigator = navigator,
                        data = item
                    )
                }
            }

        }
    }


}

@Preview
@Composable
fun ProfileLikeListScreenPreview() {
    val babsangListViewModel: BabsangListViewModel = hiltViewModel()
    val navController = rememberNavController()
    val navigator = ProfileNavigator(navController)

    AlddeulBabsangTheme {
        ProfileLikeListScreen(
            navigator = navigator,
            data = BabsangListEntity(
                id = 1,
                avatar = "",
                name = "송이네 밥상",
                codeName = "경양식/일식",
                address = "서울특별시 용산구 청파동 11",
                phone = "02-210-0220",
                favorite = true
            ),
            onBackClick = { },
            babsangListViewModel = babsangListViewModel
        )


    }
}