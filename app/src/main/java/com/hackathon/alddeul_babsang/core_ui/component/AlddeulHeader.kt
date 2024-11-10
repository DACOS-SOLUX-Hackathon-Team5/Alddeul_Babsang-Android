package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi

@Composable
fun AlddeulHeader(
    text: Int
) {
    Text(
        modifier = Modifier.padding(top = 20.dp, bottom = 12.dp),
        text = stringResource(id = text),
        style = head7Semi,
        color = Gray900
    )
}