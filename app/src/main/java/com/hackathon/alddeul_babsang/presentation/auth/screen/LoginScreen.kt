package com.hackathon.alddeul_babsang.presentation.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulButton
import com.hackathon.alddeul_babsang.core_ui.component.AuthTextField
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray100
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.Peach100
import com.hackathon.alddeul_babsang.core_ui.theme.Peach50
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.bmDohyeonRegular
import com.hackathon.alddeul_babsang.presentation.auth.navigation.AuthNavigator
import com.hackathon.alddeul_babsang.util.toast
import kotlin.contracts.contract

@Composable
fun LoginRoute(
    navigator: AuthNavigator
) {
    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Peach100
        )
    }

    LoginScreen(
        onLoginClick = {
            keyboardController?.hide()
            navigator.navigateMain()
        },
        onSignUpClick = { navigator.navigateSignUp1() }
    )
}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    val verticalGradient = Brush.verticalGradient(
        colors = listOf(Peach50, White),
        startY = 0.0f,
        endY = 9000f,
    )
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(verticalGradient)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = stringResource(R.string.tv_login_title),
            style = bmDohyeonRegular,
            color = Orange900,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AuthTextField(
                value = id,
                onValueChange = { id = it },
                placeholderText = stringResource(R.string.tv_login_id_placeholder)
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthTextField(
                value = password,
                onValueChange = { password = it },
                placeholderText = stringResource(R.string.tv_login_password_placeholder)
            )
            Spacer(modifier = Modifier.weight(1f))
            AlddeulButton(text = R.string.btn_login) {
                if (id.isNotBlank() && password.isNotBlank()) {
                    onLoginClick()
                } else {
                    context.toast(context.getString(R.string.toast_login_failure))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            AlddeulButton(
                text = R.string.btn_login_signup,
                textColor = Gray200,
                color = White,
                stroke = Gray100
            ) {
                onSignUpClick()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AlddeulBabsangTheme {
        LoginScreen()
    }
}