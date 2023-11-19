package by.mihaltsov.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.mihaltsov.core.model.CardQueueModel

@Composable
fun CardV(model: CardQueueModel, onClick: (Int) -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(model.queueNumber)
                }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                Text(
                    text = model.queueNumber.toString(),
                    modifier = Modifier
                        .padding(16.dp)
                        .sizeIn(minWidth = 50.dp)
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                        .width(1.dp)
                )
                Text(
                    text = model.nickName
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardVPreview() {

    CardV(
        CardQueueModel(
            nickName = "Mihaltsov",
            queueNumber = 1,
            oldPosition = 0,
            newPosition = 2
        )
    )
}

@Preview
@Composable
private fun CardVPreview11() {
    CardV(
        CardQueueModel(
            nickName = "Mihaltsov",
            queueNumber = 11,
            oldPosition = 0,
            newPosition = 2
        )
    )
}


@Preview
@Composable
private fun CardVPreviewWith1111() {
    CardV(
        CardQueueModel(
            nickName = "Mihaltsov",
            queueNumber = 1111,
            oldPosition = 0,
            newPosition = 2
        )
    )
}