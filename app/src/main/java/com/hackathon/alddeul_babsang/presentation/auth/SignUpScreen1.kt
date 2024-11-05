package com.hackathon.alddeul_babsang.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulButton
import com.hackathon.alddeul_babsang.core_ui.component.AuthTextField
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold

@Composable
fun SignUpScreen1(navController: NavController) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 145.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            stringResource(R.string.tv_signup_setIdPassword),
            textAlign = TextAlign.Center,
            style = head4Bold,
            lineHeight = 25.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        AuthTextField(
            value = id,
            onValueChange = { id = it },
            placeholderText = stringResource(R.string.tf_signup_setId)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            placeholderText = stringResource(R.string.tf_signup_setPassword)
        )
        Spacer(modifier = Modifier.weight(1f))
        AlddeulButton(
            text = R.string.btn_signup_next,
            onClick = {
                if (id.isNotEmpty() && password.isNotEmpty()) {
                    //navController.navigate("signUp2")
                }
            }
        )
    }
}


@Preview
@Composable
fun SignUpScreen1Preview() {
    AlddeulBabsangTheme {
        SignUpScreen1(
            navController = rememberNavController()
        )
    }
}
