package com.hackathon.alddeul_babsang.presentation.profile.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray500
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body2Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head5Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head7Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator


@Composable
fun ProfileRoute(
    navigator: ProfileNavigator
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    ProfileScreen(
        navController = navigator.navController,
        onBackClick = { navigator.navigateLogin() }
    )
}

@Composable
fun ProfileScreen(
    navController: NavController,
    onBackClick: () -> Unit = {},
) {
    val context = LocalContext.current
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetKeyword by remember { mutableStateOf("") }
    var bottomSheetQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        ) {
        Row(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(30.dp))
            AsyncImage(
                modifier = Modifier
                    .size(107.dp)
                    .clip(CircleShape)
                    .border(5.dp, Color(0xFFFAB935), CircleShape),
                model = "",
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(30.dp))

            Column {
                Spacer(modifier = Modifier.height(25.dp))
                Text("닉네임", style = head4Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        stringResource(R.string.tv_profile_likelist),
                        style = head7Semi,
                        color = Gray300
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Gray300),
                        modifier = Modifier
                            .graphicsLayer(scaleX = -1f) // 좌우 반전
                            .clickable(onClick = {
                                navController.navigate("profileLikeList")
                            })
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            modifier = Modifier
                .height(200.dp)
        ) {
            Text(
                "이용안내", style = head6Semi, modifier = Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth()
                    .height(30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "서비스 이용약관",
                onClick = {
                    navigateToWebsite(
                        context = context,
                        "https://flowery-alloy-47e.notion.site/1336b8d0ab7b8019a7a9da0d80ff285b"
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "개인정보 처리방침",
                onClick = {
                    navigateToWebsite(
                        context = context,
                        "https://flowery-alloy-47e.notion.site/1336b8d0ab7b80949a59fe847a7e94d8"
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "버전 정보",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .height(200.dp)
        ) {
            Text(
                "기타", style = head6Semi, modifier = Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth()
                    .height(30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "고객센터",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "로그아웃",
                onClick = {
                    bottomSheetKeyword = "로그아웃"
                    showBottomSheet = true
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "회원 탈퇴",
                onClick = {
                    bottomSheetKeyword = "회원 탈퇴"
                    showBottomSheet = true
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
        if (showBottomSheet) {
            BottomSheetContent(
                keyword = bottomSheetKeyword,
                onDismiss = {
                    showBottomSheet = false
                },
                onConfirm = {
                    //백엔드로 쿼리 전달
                    onBackClick()

                    showBottomSheet = false
                }
            )
        }
    }
}


fun navigateToWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    keyword: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var tv1 = ""
    var tv2 = ""
    if (keyword == "로그아웃") {
        tv1 = stringResource(R.string.tv_profile_asklogout)
        tv2 = stringResource(R.string.tv_profile_asklogout2)
    }

    if (keyword == "회원 탈퇴") {
        tv1 = stringResource(R.string.tv_profile_askdelete)
        tv2 = stringResource(R.string.tv_profile_askdelete2)
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        containerColor = White,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = tv1,
                style = head5Semi,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = tv2,
                style = body2Semi,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier.height(41.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gray500,
                    ),
                    shape = RoundedCornerShape(40.dp),
                    contentPadding = PaddingValues(vertical = 19.dp),
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.btn_profile_cancel),
                        color = White,
                        style = head7Bold
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange900,
                    ),
                    shape = RoundedCornerShape(40.dp),
                    contentPadding = PaddingValues(vertical = 19.dp),
                    onClick = { onConfirm() }
                ) {
                    Text(
                        text = keyword,
                        color = White,
                        style = head7Bold
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    AlddeulBabsangTheme {
        ProfileScreen(
            navController = rememberNavController()
        )
    }
}
