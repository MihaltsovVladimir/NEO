package com.mihaltsov.core.ui

import com.mihaltsov.core.model.QueueData

sealed interface QueueUiState {

    data object Loading : QueueUiState

    data class Success(val feed: List<QueueData>) : QueueUiState
}
