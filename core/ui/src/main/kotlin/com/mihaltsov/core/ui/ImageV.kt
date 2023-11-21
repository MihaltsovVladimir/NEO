package com.mihaltsov.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihaltsov.core.designsystem.theme.NeoTheme

@Composable
fun ImageV(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(R.drawable.v),
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(percent = 25))
            .clickable {
                onClick()
            },
        contentDescription = null,
        alignment = Alignment.CenterEnd,

        )
}

@Preview()
@Composable
fun ImageVPreview() {
    NeoTheme {
        ImageV()
    }
}