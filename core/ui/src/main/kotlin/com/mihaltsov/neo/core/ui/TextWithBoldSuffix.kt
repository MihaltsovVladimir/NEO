package com.mihaltsov.neo.core.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme

@Composable
fun TextWithBoldSuffix(
    prefix: String,
    suffix: String,
    modifier: Modifier
) {
    Text(
        text = buildAnnotatedString {
            append(prefix)
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(suffix)
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun TextWithBoldSuffixPreview() {
    NeoTheme {
        TextWithBoldSuffix(
            prefix = "first:",
            suffix = "second",
            modifier = Modifier
        )
    }
}