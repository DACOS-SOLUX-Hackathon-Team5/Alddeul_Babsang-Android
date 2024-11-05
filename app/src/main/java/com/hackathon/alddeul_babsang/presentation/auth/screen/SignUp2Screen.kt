package com.hackathon.alddeul_babsang.presentation.auth.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulButton
import com.hackathon.alddeul_babsang.core_ui.component.AuthTextField
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray50
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Red
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.Yellow
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.presentation.auth.navigation.AuthNavigator
import com.hackathon.alddeul_babsang.util.toast

@Composable
fun SignUp2Route(
    navigator: AuthNavigator
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SignUp2Screen(
        onBackClick = { navigator.navigateBack() },
        onNextClick = {
            keyboardController?.hide()
            navigator.navigateMain()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp2Screen(
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    var nickname by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let { imageUri = it }
        }
    )
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(White),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.tv_signup_title),
                style = head4Bold,
                color = Gray900,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(42.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { galleryLauncher.launch("image/*") }
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .background(color = Gray50, shape = CircleShape)
                        .border(3.dp, Yellow, CircleShape)
                ) {
                    if (imageUri != null) {
                        Image(
                            modifier = Modifier
                                .size(110.dp)
                                .clip(CircleShape)
                                .zIndex(0f),
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    } else {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_signup_profile),
                            contentDescription = null
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 8.dp, y = 8.dp)
                        .zIndex(1f)
                        .shadow(10.dp, CircleShape, ambientColor = Gray50),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_signup_camera),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            AuthTextField(
                value = nickname,
                onValueChange = { nickname = it },
                placeholderText = stringResource(R.string.tv_signup_nickname_placeholder),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp, end = 18.dp)
                    .align(Alignment.End),
                text = stringResource(R.string.tv_signup_nickname_length, nickname.length),
                style = body4Regular,
                color = if (nickname.length <= 10) Gray200 else Red
            )
            Spacer(modifier = Modifier.weight(1f))
            AlddeulButton(text = R.string.btn_signup_complete) {
                if (nickname.length > 10) {
                    context.toast(context.getString(R.string.toast_signup_nickname_failure))
                } else if (nickname.isNotBlank() && imageUri != null) {
                    onNextClick()
                } else {
                    context.toast(context.getString(R.string.toast_signup_failure))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp2Screen() {
    AlddeulBabsangTheme {
        SignUp2Screen()
    }
}