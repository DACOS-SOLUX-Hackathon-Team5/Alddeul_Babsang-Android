package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.title4Bold

@Composable
fun AlddeulButton(
    text: Int,
    textColor: Color = White,
    color: Color = Orange900,
    stroke: Color = Orange900,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp)),
        border = BorderStroke(
            width = 1.dp,
            color = stroke
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        contentPadding = PaddingValues(vertical = 19.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = stringResource(id = text),
            style = title4Bold,
            color = textColor
        )
    }
}