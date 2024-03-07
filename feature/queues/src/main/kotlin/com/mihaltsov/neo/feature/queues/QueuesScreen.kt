package com.mihaltsov.neo.feature.queues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mihaltsov.neo.core.designsystem.theme.NeoTheme
import com.mihaltsov.neo.realtime.RealtimeDataSource
import kotlinx.coroutines.CoroutineScope

@Composable
fun QueuesRoute(
    onQueueClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QueuesViewModel = hiltViewModel(),
) {
    val listState: LazyListState = rememberLazyListState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val queuesData by viewModel.queueData.collectAsStateWithLifecycle()

    QueuesScreen(
        listState = listState,
        feedState = queuesData,
        onQueueClick = onQueueClick,
        coroutineScope = coroutineScope,
    )
}

@Composable
private fun QueuesScreen(
    listState: LazyListState,
    modifier: Modifier = Modifier,
    feedState: QueuesUiState,
    coroutineScope: CoroutineScope,
    onQueueClick: (String) -> Unit,
    realtimeRepository: RealtimeDataSource = RealtimeDataSource()
) {

    val state = rememberLazyStaggeredGridState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val number = (0..200).random()
                    realtimeRepository.addQueue("Title $number", "Queue description $number")
                          },
                modifier = Modifier
            ) {
                Icon(Icons.Default.Add, contentDescription = "Добавить очередь")
            }
        },
    ) {
        Box(modifier = modifier.padding(it).fillMaxSize()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 24.dp,
                modifier = Modifier,
                state = state,
            ) {

                queuesFeed(
                    feedState = feedState,
                    onQueueClick = onQueueClick,
                )
                item(span = StaggeredGridItemSpan.FullLine, contentType = "bottomSpacing") {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        // Add space for the content to clear the "offline" snackbar.
                        // TODO: Check that the Scaffold handles this correctly in NiaApp
                        // if (isOffline) Spacer(modifier = Modifier.height(48.dp))
                        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun TestQueueScreenPreview() {
    NeoTheme {
        QueuesRoute({})
    }
}