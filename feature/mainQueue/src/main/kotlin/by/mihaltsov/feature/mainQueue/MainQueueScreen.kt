package by.mihaltsov.feature.mainQueue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.R
import by.mihaltsov.core.designsystem.theme.NEOTheme
import by.mihaltsov.core.model.CardQueueModel
import by.mihaltsov.core.model.UserData
import by.mihaltsov.core.ui.ButtonV
import by.mihaltsov.core.ui.CardV
import by.mihaltsov.core.ui.ImageV
import by.mihaltsov.core.ui.TitleTextV

@Composable
fun MainQueueScreen(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
    ) {
        Content(
            Modifier
                .fillMaxHeight()
                .weight(1f)
        )
        ButtonV(buttonText = "Click")
    }
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState()),

        ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            strokeWidth = 15.dp
        )
        TitleTextV()
        ImageV()
        TextField(
            modifier = Modifier.clip(RoundedCornerShape(percent = 25)),
            value = "Text field",
            onValueChange = { println(it) },
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
        val model = CardQueueModel(
            nickName = "Mihaltsov",
            queueNumber = 1,
            oldPosition = 0,
            newPosition = 2
        )
        CardV(model)
    }
}

@Preview
@Composable
private fun MainQueueScreenPreview() {
    NEOTheme {
        MainQueueScreen()
    }
}