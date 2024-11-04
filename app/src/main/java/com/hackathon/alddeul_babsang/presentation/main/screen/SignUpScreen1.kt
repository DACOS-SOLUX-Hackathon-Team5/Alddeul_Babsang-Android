package com.hackathon.alddeul_babsang.presentation.main.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.core_ui.theme.pretendardBold
import com.hackathon.alddeul_babsang.core_ui.theme.*

@Composable
fun SignUpScreen1(navController: NavController) {
    val (department, setDepartment) = remember {
        mutableStateOf("")
    }

    val (name, setName) = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

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
            "아이디와 비밀번호를\n설정해주세요",
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
            fontFamily = pretendardBold,
            lineHeight = 25.sp

            )
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            value = department,
            onValueChange = setDepartment,
            placeholder = {
                Text(
                    "아이디 입력",
                    fontSize = 14.sp,
                    color = Gray300,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White, // 포커스된 상태에서 배경색을 흰색으로 설정
                unfocusedContainerColor = Color.White, // 비포커스 상태에서 배경색을 흰색으로 설정
                cursorColor = Color.Black,
                focusedIndicatorColor = Orange900,
                unfocusedIndicatorColor = Gray300
            )
        )
        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = department,
            onValueChange = setDepartment,
            placeholder = {
                Text(
                    "비밀번호 입력",
                    fontSize = 14.sp,
                    color = Gray300,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = Orange900,
                focusedIndicatorColor = Orange900,
                unfocusedIndicatorColor = Gray300
            )
        )
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .width(350.dp)
                .height(100.dp)
                .padding(bottom = 50.dp),

            onClick = {
            if (name.isNotEmpty() and department.isNotEmpty()) {
                Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                navController.navigate("greeting")
            }
        }) { Text("다음", fontSize = 16.sp, fontFamily = pretendardBold) }

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
