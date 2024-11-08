package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.hackathon.alddeul_babsang.domain.entity.BabsangRecommendEntity

@Composable
fun DetailRecommendedItem(
    data: BabsangRecommendEntity
) {
    Column {
        AsyncImage(
            model = data.avatar ?: when (data.codeName) {
                "한식" -> R.drawable.ic_korean_food
                "중식" -> R.drawable.ic_chinese_food
                "경양식/일식" -> R.drawable.ic_japanese_food
                else -> R.drawable.ic_etc_food
            },
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(200.dp)
                .background(
                    color = when (data.codeName) {
                        "한식" -> Orange700
                        "중식" -> Yellow
                        "경양식/일식" -> Pink
                        "기타외식업" -> Blue
                        else -> Font_B04
                    },
                    shape = RoundedCornerShape(14.dp)
                ),
            contentScale = if (data.avatar == null) ContentScale.None else ContentScale.FillBounds,
            alignment = Alignment.Center
        )
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = data.name,
            style = body1Semi,
            color = Black
        )
        Text(
            text = data.codeName,
            style = body4Regular,
            color = Font_B04
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailRecommendedItemPreview() {
    AlddeulBabsangTheme {
        DetailRecommendedItem(
            data = BabsangRecommendEntity(
                id = 1,
                avatar = null,
                name = "족발 야시장",
                codeName = "한식",
                address = "용산 동자동",
            )
        )
    }
}