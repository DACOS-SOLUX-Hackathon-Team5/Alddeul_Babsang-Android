package com.hackathon.alddeul_babsang.presentation.report.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.hackathon.alddeul_babsang.R
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulButton
import com.hackathon.alddeul_babsang.core_ui.component.AlddeulHeader
import com.hackathon.alddeul_babsang.core_ui.component.ReportWriteArea
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.Gray100
import com.hackathon.alddeul_babsang.core_ui.theme.Gray600
import com.hackathon.alddeul_babsang.core_ui.theme.Gray900
import com.hackathon.alddeul_babsang.core_ui.theme.Orange800
import com.hackathon.alddeul_babsang.core_ui.theme.White
import com.hackathon.alddeul_babsang.core_ui.theme.body2Semi
import com.hackathon.alddeul_babsang.core_ui.theme.head4Bold
import com.hackathon.alddeul_babsang.presentation.report.navigation.ReportNavigator

@Composable
fun ReportWriteRoute(
    navigator: ReportNavigator
) {
    ReportWriteScreen(
        onBackClick = { navigator.navigateBack() },
        onButtonClick = { navigator.navigateBack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportWriteScreen(
    onBackClick: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let { imageUri = it }
        }
    )
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var menu1 by remember { mutableStateOf("") }
    var menu1price by remember { mutableStateOf("") }
    var menu2 by remember { mutableStateOf("") }
    var menu2price by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val categoryList = listOf("한식", "중식", "경양식/일식", "기타 외식업")
    var selectedCategory by remember { mutableIntStateOf(0) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.background(White),
                title = {
                    Text(
                        text = stringResource(R.string.appbar_report_title),
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
                .verticalScroll(scrollState)
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
            Spacer(modifier = Modifier.height(8.dp))
            ReportWriteArea(
                text = R.string.tv_report_name,
                value = name,
                onValueChange = { name = it },
                placeholder = R.string.tv_report_name_placeholder
            )
            ReportWriteArea(
                text = R.string.tv_report_address,
                value = address,
                onValueChange = { address = it },
                placeholder = R.string.tv_report_address_placeholder
            )
            AlddeulHeader(text = R.string.tv_report_category)
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                itemsIndexed(categoryList) { index, category ->
                    Button(
                        shape = RoundedCornerShape(14.dp),
                        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedCategory == index) Orange800 else Gray100
                        ),
                        onClick = { selectedCategory = index }
                    ) {
                        Text(
                            text = category,
                            style = body2Semi,
                            color = if (selectedCategory == index) White else Gray600
                        )
                    }
                }
            }
            ReportWriteArea(
                text = R.string.tv_report_phone,
                value = phone,
                onValueChange = { phone = it },
                placeholder = R.string.tv_report_phone_placeholder
            )
            ReportWriteArea(
                text = R.string.tv_report_menu1,
                value = menu1,
                onValueChange = { menu1 = it },
                placeholder = R.string.tv_report_menu1_placeholder
            )
            ReportWriteArea(
                text = R.string.tv_report_menu1_price,
                value = menu1price,
                onValueChange = { menu1price = it },
                placeholder = R.string.tv_report_price_placeholder
            )
            ReportWriteArea(
                text = R.string.tv_report_menu2,
                value = menu2,
                onValueChange = { menu2 = it },
                placeholder = R.string.tv_report_menu2_placeholder
            )
            ReportWriteArea(
                text = R.string.tv_report_menu2_price,
                value = menu2price,
                onValueChange = { menu2price = it },
                placeholder = R.string.tv_report_price_placeholder
            )
            Spacer(modifier = Modifier.height(110.dp))
            AlddeulButton(text = R.string.btn_report_complete) {
                if (name.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty() && menu1.isNotEmpty() && menu1price.isNotEmpty()) {
                    onButtonClick()
                }
                onButtonClick()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportWriteScreenPreview() {
    AlddeulBabsangTheme {
        ReportWriteScreen()
    }
}