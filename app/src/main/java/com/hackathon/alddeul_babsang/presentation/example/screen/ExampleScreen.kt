package com.hackathon.alddeul_babsang.presentation.example.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackathon.alddeul_babsang.core_ui.theme.AlddeulBabsangTheme
import com.hackathon.alddeul_babsang.domain.entity.ExampleEntity
import com.hackathon.alddeul_babsang.presentation.example.navigation.ExampleNavigator
import com.hackathon.alddeul_babsang.util.UiState

@Composable
fun ExampleRoute(
    navigator: ExampleNavigator
) {
    val exampleViewModel: ExampleViewModel = hiltViewModel()

    ExampleScreen(
        exampleViewModel = exampleViewModel
    )
}

@Composable
fun ExampleScreen(
    exampleViewModel: ExampleViewModel
) {
    val getExampleState by exampleViewModel.getExampleState.collectAsStateWithLifecycle(UiState.Empty)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        when (getExampleState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is UiState.Success -> {
                val data = (getExampleState as UiState.Success<List<ExampleEntity>>).data
                LazyColumn {
                    itemsIndexed(data) { index, item ->
                        ExampleItem(data = item)
                    }
                }
            }

            is UiState.Failure -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Failed to load calendar data",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            else -> {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleScreenPreview() {
    AlddeulBabsangTheme {
        ExampleScreen(
            exampleViewModel = hiltViewModel()
        )
    }
}

