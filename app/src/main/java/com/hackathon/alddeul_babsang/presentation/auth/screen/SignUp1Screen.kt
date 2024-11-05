package com.hackathon.alddeul_babsang.presentation.auth.screen

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulButton
import com.hackathon.alddeul_babsang.core_ui.component.AuthTextField
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.presentation.auth.navigation.AuthNavigator

@Composable
fun SignUp1Route(
    navigator: AuthNavigator
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    SignUp1Screen(
        onNextClick = {
            keyboardController?.hide()
            navigator.navigateSignUp2()
        }
    )
}

@Composable
fun SignUp1Screen(
    onNextClick: () -> Unit = {}
) {
    var id by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(110.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.tv_signup_setIdPassword),
            textAlign = TextAlign.Center,
            style = head4Bold,
            color = Gray900
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
                    onNextClick()
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreen1Preview() {
    AlddeulBabsangTheme {
        SignUp1Screen()
    }
}
