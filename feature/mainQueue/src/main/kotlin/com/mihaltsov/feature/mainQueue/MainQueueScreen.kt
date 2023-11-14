package com.mihaltsov.feature.mainQueue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MainQueueScreen(modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterEnd),
            strokeWidth = 15.dp
        )
        TitleTextV(Modifier.align(Alignment.BottomCenter))
        ImageV(
            Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
private fun MainQueueScreenPreview() {
    MainQueueScreen(Modifier)
}