package by.mihaltsov.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import by.mihaltsov.core.designsystem.theme.NEOTheme

@Composable
fun RowV(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.ic_call_answer
            ),
            contentDescription = null,
        )
        Spacer(Modifier.width(20.dp))
        Icon(
            painter = painterResource(
                id = R.drawable.ic_call_answer
            ),
            contentDescription = null,
        )
        Icon(
            modifier = Modifier.padding(top = 20.dp),
            painter = painterResource(
                id = R.drawable.ic_call_answer
            ),
            contentDescription = null,
        )
        Spacer(Modifier.width(20.dp))
        Icon(
            painter = painterResource(
                id = R.drawable.ic_call_answer
            ),
            contentDescription = null,
        )
    }
}

@Preview()
@Composable
private fun RowVPreview() {
    NEOTheme {
        RowV()
    }
}