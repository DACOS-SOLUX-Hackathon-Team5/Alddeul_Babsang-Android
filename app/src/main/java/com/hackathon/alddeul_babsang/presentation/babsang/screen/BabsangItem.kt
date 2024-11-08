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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.body2Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.data.dto.response.ResponseBabsangDto
import com.hackathon.alddeul_babsang.presentation.profile.screen.LikeViewModel

@Composable
fun BabsangItem(
    onClick: () -> Unit = {},
    data: ResponseBabsangDto
) {
    var isFavorite by remember { mutableStateOf(data.favorite) }
    val likeViewModel: LikeViewModel = hiltViewModel()

    // 클릭할 때마다 favorite 값 토글
    val heartIconId = if (isFavorite) {
        R.drawable.ic_heart_red
    } else {
        R.drawable.ic_heart_white
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Orange700,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = { onClick() })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
        ) {
            // AsyncImage 로드
            ReplaceImage2(data.category, data.imageUrl)
            Image(
                painter = painterResource(heartIconId),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp, top = 10.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        // 클릭 시 좋아요 상태를 토글
                        isFavorite = !isFavorite
                        likeViewModel.postLike(storeId = data.storeId)
                    }
            )
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
                text = when (data.category){
                    "KOREAN" -> "한식"
                    "WESTERN_JAPANESE" -> "경양식/일식"
                    "CHINESE" -> "중식"
                    else -> "기타외식업"
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
            text = data.address,
            style = body4Regular,
            color = Gray300,
            modifier = Modifier.padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = data.contact,
            style = body4Regular,
            color = Gray300,
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)
        )
    }
}

@Composable
fun ReplaceImage2(codeName: String, imageUrl: String?) {
    val imageId = when (codeName) {
        "WESTERN_JAPANESE" -> R.drawable.ic_japanese_food
        "KOREAN" -> R.drawable.ic_korean_food
        "CHINESE" -> R.drawable.ic_chinese_food
        else -> R.drawable.ic_etc_food
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
                    .padding(vertical = 15.dp)
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