package com.mihaltsov.neo.feature.queues

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * An extension on [LazyListScope] defining a feed with queues widgets.
 * Depending on the [feedState], this might emit no items.
 */
@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridScope.queuesFeed(
    feedState: QueuesUiState,
    onQueueClick: (String) -> Unit,
) {

    when (feedState) {

        is QueuesUiState.Loading, QueuesUiState.LoadFailed -> Unit

        is QueuesUiState.Success -> {
            items(
                items = feedState.feed,
                key = { it.id },
                contentType = { "queueFeedItem" },
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.clickable {
                        onQueueClick(it.id)
                    }
                ) {
                    Column() {
                        Text(
                            text = it.title,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = it.description,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}