package com.mihaltsov.neo.core.ui

import com.mihaltsov.neo.core.model.QueueData

sealed interface QueueUiState {

    data object Loading : QueueUiState

    data class Success(val feed: List<QueueData>) : QueueUiState
}
