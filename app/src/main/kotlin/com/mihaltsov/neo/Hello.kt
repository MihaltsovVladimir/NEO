package com.mihaltsov.neo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Preview
@Composable
fun HelloPreview(
    @PreviewParameter(Hello::class)
    string: String
) {
    Hello()
}

class Hello : PreviewParameterProvider<String> {

    override val values: Sequence<String> = sequenceOf("Hello", "Bye")

}