package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B04
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray600
import com.hackathon.alddeul_babsang.core_ui.theme.body3Semi
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.domain.entity.ReviewEntity
import kotlin.math.round

@Composable
fun ReviewItem(
    data: ReviewEntity
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp, vertical = 17.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = data.avatar,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .size(85.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.nickname,
                    style = body3Semi,
                    color = Gray600
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = data.createdAt,
                    style = body4Regular,
                    color = Font_B04
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                imageVector = when(round(data.star)) {
                    in 0.0..1.4 -> ImageVector.vectorResource(id = R.drawable.ic_review_star_one)
                    in 1.5..2.4 -> ImageVector.vectorResource(id = R.drawable.ic_review_star_two)
                    in 2.5..3.4 -> ImageVector.vectorResource(id = R.drawable.ic_review_star_three)
                    in 3.5..4.4 -> ImageVector.vectorResource(id = R.drawable.ic_review_star_four)
                    in 4.5..5.0 -> ImageVector.vectorResource(id = R.drawable.ic_review_star_five)
                    else -> throw IllegalArgumentException("Invalid star value")
                },
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data.content,
                style = body4Regular,
                color = Font_B04,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewItemPreview() {
    AlddeulBabsangTheme {
        ReviewItem(
            data = ReviewEntity(
                id = 1,
                avatar = "",
                nickname = "최강 눈송이",
                createdAt = "24.03.12",
                star = 4.8,
                content = "최고의 맛집입니다.\n또 와서 먹고 싶어요 ㅎㅎ\nㅋ",
            )
        )
    }
}