package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body2Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body7Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head5Bold
import com.hackathon.alddeul_babsang.domain.entity.BabsangListEntity
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity
import com.hackathon.alddeul_babsang.presentation.babsang.navigation.BabsangNavigator

@Composable
fun BabsangRecommendItem(
    navigator: BabsangNavigator,
    data: BabsangRecommendEntity
) {

    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Orange700,
                shape = RoundedCornerShape(14.dp)
            )
            .width(170.dp)
            .height(192.dp)
            .clickable(onClick = {
                navigator.navigateDetail(data.id)
            })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
        ) {
            // AsyncImage 로드
            ReplaceImage(data.codeName, data.avatar)

            Column (
                modifier = Modifier
                    .padding(top=110.dp)
                    .padding(horizontal = 10.dp)
            ){
                Box(
                    modifier = Modifier
                        .width(34.dp)
                        .height(17.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(White),

                    ){
                    Text(data.address, textAlign = TextAlign.Center, style = body7Semi)
                }

                Spacer(modifier = Modifier.height(7.dp))

                Text(data.name, style = head5Bold, color = White)

                Spacer(modifier = Modifier.height(7.dp))

                Text(data.codeName, style = body4Regular, color = White)

            }

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Text(
                text = data.name,
                style = head4Bold,
                color = Orange900,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = data.codeName,
                style = body2Regular,
                color = Orange800,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 3.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = data.address,
            style = body4Regular,
            color = Gray300,
            modifier = Modifier.padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(7.dp))

    }
}

@Composable
fun ReplaceImage(codeName: String, imageUrl: String?) {
    val imageId = when (codeName) {
        "경양식/일식" -> R.drawable.ic_japanese_food
        "한식" -> R.drawable.ic_korean_food
        "중식" -> R.drawable.ic_chinese_food
        else -> R.drawable.ic_etc_food
    }

    val backgroundColor = when (codeName) {
        "경양식/일식" -> Orange700
        "한식" -> Color(0xFAB935)
        "중식" -> Color(0xFAB935)
        else -> Color(0xFAB935)
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Orange700) // 배경색 설정
    ) {
        if (imageUrl.isNullOrEmpty()) {
            // imageUrl이 null이나 empty일 경우 대체 이미지 표시
            Image(
                painter = painterResource(id = imageId),  // 대체 이미지
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 80.dp)
                    .padding(top = 15.dp)
                    .background(color = backgroundColor)
            )
        } else {
            // imageUrl이 null이 아니면 AsyncImage로 비동기 이미지 로드
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                placeholder = painterResource(id = imageId),
                contentScale = ContentScale.Crop,  // 이미지 비율 유지, 크기 확대 또는 자르기
                modifier = Modifier
                    .fillMaxWidth()    // 가로는 꽉 차게
                    .fillMaxHeight()   // 세로도 꽉 차게
                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))

            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BabsangRecommendItemPreview() {
    val navController = rememberNavController()
    val navigator = BabsangNavigator(navController)

    AlddeulBabsangTheme {
        BabsangRecommendItem(
            navigator = navigator,
            data = BabsangRecommendEntity(
                id = 1,
                avatar = null,
                name = "송이네 밥상",
                codeName = "경양식/일식",
                address = "용산구",
            )
        )
    }
}