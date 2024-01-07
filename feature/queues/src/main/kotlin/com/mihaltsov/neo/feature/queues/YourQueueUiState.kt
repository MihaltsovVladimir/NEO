package com.mihaltsov.neo.feature.queues

import com.mihaltsov.neo.core.model.QueueItemData

sealed interface QueuesUiState {

    data object Loading : QueuesUiState

    data object LoadFailed : QueuesUiState

    data class Success(val feed: List<QueueItemData> = listOf()) : QueuesUiState
}