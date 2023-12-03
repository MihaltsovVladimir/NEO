package com.mihaltsov.neo.feature.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mihaltsov.neo.core.designsystem.component.DynamicAsyncImage
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme
import com.mihaltsov.neo.core.ui.ImageV
import com.mihaltsov.neo.core.ui.TitleTextV

@Composable
fun AuthorizationRoute(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel = hiltViewModel(),
) {

    val textFieldValue by viewModel.inputTextFieldValue.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
    ) {
        AuthorizationScreen(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            textFieldValue = textFieldValue,
            onTextFieldChanged = viewModel::onTextFieldChanged
        )
    }
}

@Composable
private fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    textFieldValue: String = "",
    onTextFieldChanged: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState()),

        ) {
        TitleTextV()
        ImageV()
        DynamicAsyncImage(
            imageUrl = "https://api.qrserver.com/v1/create-qr-code/?data=HelloWorld&amp;size=10x10",
            modifier = Modifier
                .padding(10.dp)
                .size(100.dp),
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = 25)),
            value = textFieldValue,
            onValueChange = { onTextFieldChanged(it) },
            placeholder = { Text(text = "plsh") },
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_call_answer
                    ),
                    contentDescription = null,
                )
            }
        )
    }
}

@Preview
@Composable
private fun MainQueueScreenPreview() {
    NeoTheme {
        AuthorizationRoute()
    }
}