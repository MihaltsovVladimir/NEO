package com.mihaltsov.neo.core.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme

@Composable
fun ApplyButton(
    buttonText: String, onClick: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(end = 20.dp)
        )
    }
}

@Preview()
@Composable
private fun ButtonVPreview() {
    NeoTheme {
        ApplyButton(buttonText = "Some text", onClick = {}, modifier = Modifier)
    }
}