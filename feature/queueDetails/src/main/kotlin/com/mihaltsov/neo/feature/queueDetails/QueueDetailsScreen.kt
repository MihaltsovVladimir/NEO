package com.mihaltsov.neo.feature.queueDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme
import com.mihaltsov.neo.core.model.QueuePersonCardModel
import com.mihaltsov.neo.core.ui.ApplyButton
import com.mihaltsov.neo.core.ui.PersonQueueCard

@Composable
fun QueueDetailsRoute(
    viewModel: QueueDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()
    val queueData by viewModel.queueData.collectAsStateWithLifecycle()

    QueueDetailScreen(
        listState = listState,
        uiState = queueData,
        onItemClick = { println(" number $it") },
        onApplyButtonClick = { viewModel.applyToQueue()},
    )
}

@Composable
private fun QueueDetailScreen(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    uiState: QueueDetailsUiState,
    onApplyButtonClick: () -> Unit,
    onItemClick: (Int) -> Unit = {},
) {

    when (uiState) {
        is QueueDetailsUiState.Loading -> {}


        is QueueDetailsUiState.LoadFailed -> {}

        is QueueDetailsUiState.Success -> {
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
                    uiState.uiDataModel.persons.forEach {
                        item {
                            PersonQueueCard(
                                model = QueuePersonCardModel(
                                    nickName = it.nickName,
                                    queueNumber = it.queueNumber,
                                    isActive = true,
                                    isMine = it.isMine
                                ),
                                onClick = onItemClick
                            )
                        }
                    }
                }
                val hasInQueue = uiState.uiDataModel.persons.any { it.isMine }
                if (!hasInQueue) {
                    ApplyButton(
                        buttonText = stringResource(R.string.queue_details_apply_to_queue),
                        modifier = Modifier,
                        onClick = {
                            onApplyButtonClick()
                        })
                }
            }
        }
    }
}

@Preview
@Composable
private fun TestQueueScreenPreview() {
    NeoTheme {
        QueueDetailsRoute(onBackClick = {})
    }
}