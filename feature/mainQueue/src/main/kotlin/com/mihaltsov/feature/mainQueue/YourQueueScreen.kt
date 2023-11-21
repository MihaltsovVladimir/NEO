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
import com.mihaltsov.core.designsystem.theme.NeoTheme
import com.mihaltsov.core.model.CardQueueModel
import com.mihaltsov.core.model.QueueData
import com.mihaltsov.core.ui.ButtonV
import com.mihaltsov.core.ui.CardV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun YourQueueRoute(
    modifier: Modifier = Modifier,
    viewModel: YourQueueViewModel = hiltViewModel(),
) {
    val listState: LazyListState = rememberLazyListState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val queueData by viewModel.queueData.collectAsStateWithLifecycle()

    PeopleElectronicQueue(
        listState = listState,
        queueData = queueData,
        onClick = {
            viewModel.removeItem(it)
        },
        coroutineScope = coroutineScope,
    )
}

@Composable
private fun PeopleElectronicQueue(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    queueData: QueueData?,
    coroutineScope: CoroutineScope,
    onClick: (Int) -> Unit = {},
) {
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
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
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
        ButtonV(buttonText = "Show my position",
            modifier = Modifier,
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(10)
                }
            })
    }
}

@Preview
@Composable
private fun TestQueueScreenPreview() {
    NeoTheme {
        YourQueueRoute()
    }
}