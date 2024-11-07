package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B04
import com.hackathon.alddeul_babsang.core_ui.theme.Gray400
import com.hackathon.alddeul_babsang.core_ui.theme.body1Semi


@Composable
fun ProfileScreenItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = text,
            color = Gray400,
            style = body1Semi
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = { onClick() }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
                contentDescription = null,
                tint = Font_B04
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenItemPreview() {
    ProfileScreenItem(
        text = "개인정보 수정",
        onClick = {}
    )
}