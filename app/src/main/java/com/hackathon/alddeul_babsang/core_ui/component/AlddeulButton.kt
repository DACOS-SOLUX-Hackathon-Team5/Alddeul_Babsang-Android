package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.title4Bold

@Composable
fun AlddeulButton(
    text: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 19.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = stringResource(id = text),
            style = title4Bold,
            color = White
        )
    }
}