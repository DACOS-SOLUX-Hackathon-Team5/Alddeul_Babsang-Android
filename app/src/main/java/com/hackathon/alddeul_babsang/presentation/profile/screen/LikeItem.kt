package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B04
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray600
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.body2Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body3Semi
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.domain.entity.BabsangDetailEntity
import com.hackathon.alddeul_babsang.domain.entity.ReviewEntity
import com.hackathon.alddeul_babsang.presentation.detail.screen.DetailViewModel
import kotlin.math.round

@Composable
fun LikeItem(
    data: BabsangDetailEntity
) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .border(
                width = 1.dp,
                color = Orange700,
                shape = RoundedCornerShape(14.dp)
            )
            .height(240.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
        ) {
            // AsyncImage 로드를 위한 함수 호출
            LoadImageWithPlaceholder(data.codeName, data.avatar)

            // 아이콘을 이미지 위에 오버레이
            Image(
                painter = painterResource(id = R.drawable.ic_heart_white),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp, top = 10.dp)
                    .align(Alignment.TopEnd) // Box 내에서 오른쪽 끝으로 배치
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

        Text(
            text = data.phone,
            style = body4Regular,
            color = Gray300,
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}

@Composable
fun LoadImageWithPlaceholder(codeName: String, imageUrl: String?) {
    var imageId =R.drawable.ic_korean_food
    if (codeName =="경양식/일식"){
        imageId=R.drawable.ic_japanese_food
    }
    else if (codeName =="한식"){
        imageId=R.drawable.ic_korean_food
    }
    else if (codeName =="중식"){
        imageId=R.drawable.ic_chinese_food
    }
    else {
        imageId=R.drawable.ic_etc_food
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color= Orange700) // 배경색 설정
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = imageId), // codeName에 따라 다른 placeholder 제공
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewItemPreview() {
    val LikeListViewModel: LikeListViewModel = hiltViewModel()
    AlddeulBabsangTheme {
        LikeItem(
            data = BabsangDetailEntity(
                id = 1,
                avatar = "",
                name = "송이네 밥상",
                codeName = "경양식/일식",
                address = "서울특별시 용산구 청파동 11",
                phone = "02-210-0220",
                rating = 4.3,
                menu = "김치찌개: 8000, 된장찌개 9000",
                review = LikeListViewModel.mockReviews
            )
        )
    }
}