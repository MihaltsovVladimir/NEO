package com.mihaltsov.feature.mainQueue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mihaltsov.core.designsystem.theme.NEOTheme
import com.mihaltsov.core.model.CardQueueModel
import com.mihaltsov.core.model.QueueData
import com.mihaltsov.core.ui.ButtonV
import com.mihaltsov.core.ui.CardV
import kotlinx.coroutines.launch


@Composable
fun TestQueueScreen(
    modifier: Modifier = Modifier,
    viewModel: QueueViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val queueData by viewModel.queueData.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                bottom = 16.dp,
                end = 16.dp,
                start = 16.dp
            ),
    ) {
        PeopleElectronicQueue(
            listState = listState,
            queueData = queueData,
            onClick = {
                viewModel.removeItem(it)
            },
            modifier = Modifier.weight(1f)
        )
        ButtonV(buttonText = "Click", onClick = {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }
        })
    }
}

@Composable
private fun PeopleElectronicQueue(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    queueData: QueueData?,
    onClick: (Int) -> Unit = {},
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        queueData?.persons?.forEach {
            item {
                CardV(
                    model = CardQueueModel(
                        nickName = "Mihaltsov",
                        queueNumber = it.queueNumber,
                        oldPosition = 0,
                        newPosition = 2
                    ),
                    onClick = onClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun TestQueueScreenPreview() {
    NEOTheme {
        TestQueueScreen()
    }
}