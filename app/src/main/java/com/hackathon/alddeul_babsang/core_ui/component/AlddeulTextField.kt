package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular

@Composable
fun AlddeulTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: Int,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray200,
                shape = RoundedCornerShape(10.dp)
            )
            .height(150.dp)
            .padding(18.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = stringResource(id = placeholder),
                style = body4Regular,
                color = Gray200
            )
        },
        textStyle = body4Regular,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}