package com.mihaltsov.neo.feature.checkin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mihaltsov.neo.core.designsystem.component.DynamicAsyncImage
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme

@Composable
fun CheckInRoute(
    modifier: Modifier = Modifier,
    viewModel: CheckInViewModel = hiltViewModel(),
) {
    val textFieldValue by viewModel.inputTextFieldValue.collectAsStateWithLifecycle()
    val checkInUiState by viewModel.checkInUiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
    ) {
        CheckInScreen(
            modifier = Modifier.fillMaxSize(),
            textFieldValue = textFieldValue,
            onTextFieldChanged = viewModel::onTextFieldChanged,
            checkInUiState = checkInUiState,
        )
    }
}

@Composable
private fun CheckInScreen(
    modifier: Modifier = Modifier,
    textFieldValue: String = "",
    onTextFieldChanged: (String) -> Unit,
    checkInUiState: CheckInUiState = CheckInUiState.Loading,
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,

        ) {
        when (checkInUiState) {
            CheckInUiState.Loading,
            CheckInUiState.LoadFailed,
            -> Unit

            is CheckInUiState.Success -> {
                DynamicAsyncImage(
                    imageUrl = checkInUiState.qrModel.qrModel.qrUrl,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(500.dp),
                )
            }
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = 25)),
            value = textFieldValue,
            onValueChange = { onTextFieldChanged(it) },
            placeholder = { Text(text = "Новый Qr") },
        )
    }
}

@Preview
@Composable
private fun MainQueueScreenPreview() {
    NeoTheme {
        CheckInRoute()
    }
}