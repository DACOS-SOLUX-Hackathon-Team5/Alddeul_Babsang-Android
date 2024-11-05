package com.hackathon.alddeul_babsang.core_ui.component

import androidx.compose.runtime.Composable

@Composable
fun ReportWriteArea(
    text: Int,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: Int
) {
    AlddeulHeader(text = text)
    ReportTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder
    )
}