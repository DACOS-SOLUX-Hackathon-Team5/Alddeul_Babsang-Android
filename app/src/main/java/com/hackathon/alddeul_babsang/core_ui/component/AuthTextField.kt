package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Regular
import com.hackathon.alddeul_babsang.core_ui.theme.body1Regular

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                style = body1Regular,
                color = Gray200,
            )
        },
        textStyle = body1Regular,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Regular,
            unfocusedIndicatorColor = Regular
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AuthTextFieldPreview() {
    AuthTextField(
        value = "",
        onValueChange = {},
        placeholderText = "아이디"
    )
}
