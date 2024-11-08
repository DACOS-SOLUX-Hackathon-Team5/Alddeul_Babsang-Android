package com.hackathon.alddeul_babsang.presentation.babsang.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.Pink
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.Yellow
import com.hackathon.alddeul_babsang.core_ui.theme.body2Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body7Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head5Bold
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangRecommendDto

@Composable
fun BabsangRecommendItem(
    onClick: () -> Unit = {},
    data: ResponseBabsangRecommendDto
) {
    Column(
        modifier = Modifier
            .width(170.dp)
            .height(192.dp)
            .clickable(onClick = { onClick() })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(14.dp))
        ) {
            //카테고리별 이미지 지정
            LoadImage(data.category)
            Column(
                modifier = Modifier
                    .padding(top = 110.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(34.dp)
                        .height(17.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(data.region, textAlign = TextAlign.Center, style = body7Semi)
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 7.dp),
                    text = data.name,
                    style = head5Bold,
                    color = White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = when(data.category) {
                        "KOREAN" -> "한식"
                        "CHINESE" -> "중식"
                        "WESTERN_JAPANESE" -> "경양식/일식"
                        else -> "기타"
                    },
                    style = body4Regular,
                    color = White
                )
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
                text = when(data.category) {
                    "KOREAN" -> "한식"
                    "CHINESE" -> "중식"
                    "WESTERN_JAPANESE" -> "경양식/일식"
                    else -> "기타"
                },
                style = body2Regular,
                color = Orange800,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 3.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = data.region,
            style = body4Regular,
            color = Gray300,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))

    }
}

@Composable
fun LoadImage(codeName: String) {
    val imageId = when (codeName) {
        "WESTERN_JAPANESE" -> R.drawable.ic_japanese_food
        "KOREAN" -> R.drawable.ic_korean_food
        "CHINESE" -> R.drawable.ic_chinese_food
        else -> R.drawable.ic_etc_food
    }
    val backgroundColor = when (codeName) {
        "WESTERN_JAPANESE" -> Yellow
        "KOREAN" -> Orange700
        "CHINESE" -> Pink
        else -> Blue
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = backgroundColor) // 배경색 설정
    ) {
        Image(
            painter = painterResource(id = imageId),  // 대체 이미지
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 15.dp)
                .padding(bottom = 80.dp)
                .padding(top = 15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BabsangRecommendItemPreview() {
    AlddeulBabsangTheme {
        BabsangRecommendItem(
            data = ResponseBabsangRecommendDto(
                storeId = 1,
                name = "송이네 밥상",
                category = "한식",
                region = "용산구",
            )
        )
    }
}