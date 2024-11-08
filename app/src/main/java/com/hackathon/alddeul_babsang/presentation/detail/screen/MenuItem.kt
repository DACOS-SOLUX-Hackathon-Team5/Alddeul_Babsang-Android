package com.hackathon.alddeul_babsang.presentation.detail.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.core_ui.theme.Gray600
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.body2Semi
import com.hackathon.alddeul_babsang.data.dto.response.Menu

@Composable
fun MenuItem(
    data: Menu
) {
    Row {
        Text(
            text = data.name.ifBlank { "메뉴명 없음" },
            style = body2Semi,
            color = Gray600
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (data.price == 0) "가격 없음" else data.price.toString(),
            style = body2Semi,
            color = Orange900
        )
    }
}