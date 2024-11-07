package com.hackathon.alddeul_babsang.presentation.profile.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Font_B04
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray300
import com.hackathon.alddeul_babsang.core_ui.theme.Gray50
import com.hackathon.alddeul_babsang.core_ui.theme.Orange900
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.Yellow
import com.hackathon.alddeul_babsang.core_ui.theme.body2Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.core_ui.theme.head6Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head7Semi
import com.hackathon.alddeul_babsang.core_ui.theme.title2Semi
import com.hackathon.alddeul_babsang.core_ui.theme.title4Bold
import com.hackathon.alddeul_babsang.presentation.auth.screen.LoginViewModel
import com.hackathon.alddeul_babsang.presentation.profile.navigation.ProfileNavigator
import kotlinx.coroutines.launch


@Composable
fun ProfileRoute(
    navigator: ProfileNavigator
) {
    val systemUiController = rememberSystemUiController()
    val loginViewModel: LoginViewModel = hiltViewModel()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    ProfileScreen(
        onLikeClick = { navigator.navigateLike() },
        onBackClick = {
            loginViewModel.clear()
            navigator.navigateLogin()
        }
    )
}

@Composable
fun ProfileScreen(
    onLikeClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val context = LocalContext.current
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetKeyword by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 23.dp, end = 10.dp)
            .verticalScroll(scrollState),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .border(3.dp, Yellow, CircleShape),
                model = "",
                placeholder = painterResource(id = R.drawable.ic_signup_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 18.dp),
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 18.dp),
                    text = stringResource(R.string.tv_profile_nickname),
                    style = head4Bold
                )
                Row(
                    modifier = Modifier.clickable { onLikeClick() }
                ) {
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = stringResource(R.string.tv_profile_likelist),
                        style = head7Semi,
                        color = Font_B04
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_right_arrow),
                        contentDescription = null,
                        tint = Gray300,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }

        }
        Column(
            modifier = Modifier
                .padding(top = 65.dp)
                .fillMaxSize()
        ) {
            Text(
                stringResource(R.string.tv_profile_notice),
                style = head6Semi,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            ProfileItem(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.tv_profile_service),
                onClick = {
                    navigateToWebsite(
                        context = context,
                        context.getString(R.string.url_profile_service)
                    )
                }
            )
            ProfileItem(
                text = stringResource(R.string.tv_profile_privacy),
                onClick = {
                    navigateToWebsite(
                        context = context,
                        context.getString(R.string.url_profile_privacy)
                    )
                }
            )
            ProfileItem(
                text = stringResource(R.string.tv_profile_version),
                onClick = {}
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Gray50
            )
            Text(
                stringResource(R.string.tv_profile_etc),
                style = head6Semi,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 16.dp),
            )
            ProfileItem(
                text = stringResource(R.string.tv_profile_customer_center),
                onClick = {}
            )
            ProfileItem(
                text = stringResource(R.string.tv_profile_logout),
                onClick = {
                    bottomSheetKeyword = "로그아웃"
                    showBottomSheet = true
                }
            )
            ProfileItem(
                text = stringResource(R.string.tv_profile_quit),
                onClick = {
                    bottomSheetKeyword = "회원 탈퇴"
                    showBottomSheet = true
                }
            )
        }
        if (showBottomSheet) {
            BottomSheetContent(
                keyword = bottomSheetKeyword,
                onDismiss = {
                    showBottomSheet = false
                },
                onConfirm = {
                    onBackClick()
                    showBottomSheet = false
                }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    keyword: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

    when (keyword) {
        "로그아웃" -> {
            title = stringResource(R.string.tv_profile_asklogout)
            subtitle = stringResource(R.string.tv_profile_asklogout2)
        }

        "회원 탈퇴" -> {
            title = stringResource(R.string.tv_profile_askdelete)
            subtitle = stringResource(R.string.tv_profile_askdelete2)
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        containerColor = White,
    ) {
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = title2Semi,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = subtitle,
                style = body2Regular,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier.height(41.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gray200,
                    ),
                    shape = RoundedCornerShape(40.dp),
                    contentPadding = PaddingValues(vertical = 19.dp),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.btn_profile_cancel),
                        color = White,
                        style = title4Bold
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Orange900,
                    ),
                    shape = RoundedCornerShape(40.dp),
                    contentPadding = PaddingValues(vertical = 19.dp),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onConfirm()
                            }
                        }
                    }
                ) {
                    Text(
                        text = keyword,
                        color = White,
                        style = title4Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

fun navigateToWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AlddeulBabsangTheme {
        ProfileScreen()
    }
}
