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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.hackathon.alddeul_babsang.util.UiState
import com.hackathon.alddeul_babsang.util.toast
import com.hackathon.alddeul_babsang.util.uriToFile
import timber.log.Timber

@Composable
fun ReviewRoute(
    navigator: DetailNavigator,
    id: Long,
) {
    val reviewViewModel: ReviewViewModel = hiltViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current
    val systemUiController = rememberSystemUiController()
    val postReviewState by reviewViewModel.postReviewState.collectAsStateWithLifecycle(UiState.Empty)

    when (postReviewState) {
        is UiState.Success -> {
            keyboardController?.hide()
            navigator.navigateBack()
            Timber.d("Review post success")
        }

        is UiState.Failure -> {
            val message = (postReviewState as UiState.Failure).msg
            Timber.e("Review post failed: $message")
            LocalContext.current.toast(message)
        }

        else -> {}
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = White
        )
    }

    ReviewScreen(
        id = id,
        onBackClick = { navigator.navigateBack() },
        reviewViewModel = reviewViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    id: Long = 0,
    onBackClick: () -> Unit = {},
    reviewViewModel: ReviewViewModel
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
    var rating by remember { mutableIntStateOf(0) }

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
            StarRating(
                rating = rating,
                onRatingChange = { rating = it }
            )
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
                    if (reviewLength.length <= 100 && imageUri != null) {
                        val file = uriToFile(imageUri!!, context)
                        reviewViewModel.postReview(
                            storeId = id,
                            userId = 1,
                            rating = rating.toDouble(),
                            content = reviewLength,
                            reviewImage = file
                        )
                    } else context.toast(context.getString(R.string.toast_review_length))
                }
            )
        }
    }
}

@Composable
fun StarRating(
    rating: Int, // 현재 선택된 별 개수
    onRatingChange: (Int) -> Unit // 별 개수 변경 시 호출되는 함수
) {
    val starCount = 5

    LazyRow {
        items(starCount) { index ->
            IconButton(
                onClick = {
                    onRatingChange(index + 1) // 클릭된 별까지의 개수를 전달
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_review_star),
                    contentDescription = null,
                    tint = if (index < rating) Yellow else Gray100
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    AlddeulBabsangTheme {
        ReviewScreen(
            reviewViewModel = hiltViewModel()
        )
    }
}