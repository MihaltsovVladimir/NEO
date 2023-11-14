package com.mihaltsov.feature.mainQueue

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview()
@Composable
fun ImageV(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.v),
        modifier = modifier.clip(RoundedCornerShape(percent = 25)),
        contentDescription = null,
//        alignment = Alignment.CenterEnd,
    )
}