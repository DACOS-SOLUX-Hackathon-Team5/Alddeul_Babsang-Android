package com.hackathon.alddeul_babsang.presentation.detail.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulButton
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulHeader
import com.hackathon.alddeul_babsang.core_ui.component.ReviewTextField
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray100
import com.hackathon.alddeul_babsang.core_ui.theme.Gray200
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Red
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.Yellow
import com.hackathon.alddeul_babsang.core_ui.theme.body4Regular
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.presentation.detail.navigation.DetailNavigator
import com.hackathon.alddeul_babsang.util.toast

@Composable
fun ReviewRoute(
    navigator: DetailNavigator,
    id: Long,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    ReviewScreen(
        onBackClick = { navigator.navigateBack() },
        onCompleteClick = {
            keyboardController?.hide()
            navigator.navigateBack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    onBackClick: () -> Unit = {},
    onCompleteClick: () -> Unit = {}
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let { imageUri = it }
        }
    )
    var reviewLength by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(White),
                title = {
                    Text(
                        text = stringResource(R.string.appbar_review_title),
                        style = head4Bold,
                        color = Gray900
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            if (imageUri != null) {
                Image(
                    modifier = Modifier
                        .size(135.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .align(Alignment.CenterHorizontally)
                        .clickable { galleryLauncher.launch("image/*") },
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(135.dp)
                        .background(
                            color = Gray100,
                            shape = RoundedCornerShape(40.dp)
                        )
                        .align(Alignment.CenterHorizontally)
                        .clickable { galleryLauncher.launch("image/*") }
                ) {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_camera),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            AlddeulHeader(text = R.string.tv_review_rating)
            StarRating()
            AlddeulHeader(text = R.string.tv_review_detail)
            ReviewTextField(
                value = reviewLength,
                onValueChange = { reviewLength = it },
                placeholder = R.string.tv_review_placeholder
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.End),
                text = stringResource(R.string.tv_review_length, reviewLength.length),
                style = body4Regular,
                color = if (reviewLength.length <= 100) Gray200 else Red
            )
            Spacer(modifier = Modifier.weight(1f))
            AlddeulButton(
                text = R.string.btn_review_complete,
                onClick = {
                    if (reviewLength.length <= 100) onCompleteClick()
                    else context.toast(context.getString(R.string.toast_review_length))
                }
            )
        }
    }
}

@Composable
fun StarRating() {
    val starCount = 5
    var selectedStars by remember { mutableStateOf(List(starCount) { false }) }

    LazyRow {
        items(starCount) { index ->
            IconButton(
                onClick = {
                    selectedStars = List(selectedStars.size) { i -> i <= index }
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_review_star),
                    contentDescription = null,
                    tint = if (selectedStars[index]) Yellow else Gray100
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    AlddeulBabsangTheme {
        ReviewScreen()
    }
}