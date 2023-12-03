package com.mihaltsov.neo.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme

@Composable
fun TitleTextV(modifier: Modifier = Modifier) {
    Text(
        text = "First screen",
        modifier = modifier.padding(20.dp),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview()
@Composable
fun TitleTextVPreview() {
    NeoTheme {
        TitleTextV()
    }
}