package com.hackathon.alddeul_babsang.presentation.profile.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray400
import com.hackathon.alddeul_babsang.core_ui.theme.body1Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head5Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi


@Composable
fun ProfileScreenItem(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clickable { onClick() }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 30.dp),
            text = text,
            color = Gray400,
            style = head7Semi
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .graphicsLayer(scaleX = -1f),
            onClick = { onClick() }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Gray300
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