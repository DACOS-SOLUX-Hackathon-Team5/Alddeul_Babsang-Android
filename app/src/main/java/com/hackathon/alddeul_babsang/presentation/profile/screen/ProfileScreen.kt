package com.hackathon.alddeul_babsang.presentation.profile.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray400
import com.hackathon.alddeul_babsang.core_ui.theme.Gray500
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator



@Composable
fun ProfileRoute(
    navigator: ProfileNavigator
) {
    ProfileScreen(
        navController = navigator.navController,
    )
}

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

    ) {
        Row(modifier = Modifier.padding(top=60.dp)
            .fillMaxWidth()){
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
                Row(){
                    Text(stringResource(R.string.tv_profile_likelist), style = head7Semi, color=Gray300)
                    Spacer(modifier = Modifier.width(10.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Gray300),
                        modifier = Modifier
                            .graphicsLayer(scaleX = -1f) // 좌우 반전
                            //.clickable(onClick={
                                //navController.navigate("signUp2")
                            //})
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            modifier = Modifier
                .height(200.dp)
        ){
            Text("이용안내",style= head6Semi, modifier = Modifier
                .padding(start=30.dp)
                .fillMaxWidth()
                .height(30.dp))

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "서비스 이용약관",
                onClick = {
                    navigateToWebsite(
                        context = context,
                        "https://massive-maple-b53.notion.site/426578b24235447abccaae359549cdb7?pvs=4"
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "개인정보 처리방침",
                onClick = {
                    navigateToWebsite(
                        context = context,
                        "https://massive-maple-b53.notion.site/430e2c92b8694ad6a8b4497f3a3b4452"
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
        ){
            Text("기타",style= head6Semi, modifier = Modifier
                .padding(start=30.dp)
                .fillMaxWidth()
                .height(30.dp))

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "고객센터",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "로그아웃",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))
            ProfileScreenItem(
                text = "회원 탈퇴",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

        }





    }
}


fun navigateToWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
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
