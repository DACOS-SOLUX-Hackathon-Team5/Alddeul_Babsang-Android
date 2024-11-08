package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Black
import com.hackathon.alddeul_babsang.core_ui.theme.Blue
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B04
import com.hackathon.alddeul_babsang.core_ui.theme.Orange700
import com.hackathon.alddeul_babsang.core_ui.theme.Pink
import com.hackathon.alddeul_babsang.core_ui.theme.Yellow
import com.hackathon.alddeul_babsang.core_ui.theme.body1Semi
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.data.dto.response.ResponseDetailRecommendDto
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity
import com.hackathon.alddeul_babsang.presentation.babsang.screen.LoadImage

@Composable
fun DetailRecommendedItem(
    data: ResponseDetailRecommendDto,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.clickable { onClick() }
    ) {
        LoadImage2(data.category)

        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = data.name,
            style = body1Semi,
            color = Black
        )
        Text(
            text = data.category,
            style = body4Regular,
            color = Font_B04
        )
    }
}

@Composable
fun LoadImage2(codeName: String) {
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
            .clip(RoundedCornerShape(10.dp))
            .height(200.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(14.dp)
            )
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
fun DetailRecommendedItemPreview() {
    AlddeulBabsangTheme {
        DetailRecommendedItem(
            data = ResponseDetailRecommendDto(
                storeId = 1,
                name = "족발 야시장",
                category = "한식",
                region = "용산구",
            )
        )
    }
}