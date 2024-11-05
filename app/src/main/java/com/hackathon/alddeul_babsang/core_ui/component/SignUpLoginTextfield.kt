package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.hackathon.alddeul_babsang.core_ui.theme.*

@Composable
fun SignUpLoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                style = head4Bold,
                color = Gray300,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White, // 포커스된 상태에서 배경색을 흰색으로 설정
            unfocusedContainerColor = Color.White, // 비포커스 상태에서 배경색을 흰색으로 설정
            cursorColor = Color.Black,
            focusedIndicatorColor = Orange900,
            unfocusedIndicatorColor = Gray300
        )
    )
}
