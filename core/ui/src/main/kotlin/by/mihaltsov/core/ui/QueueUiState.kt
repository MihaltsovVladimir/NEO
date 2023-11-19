package by.mihaltsov.core.ui

import by.mihaltsov.core.model.QueueData

sealed interface QueueUiState {

    data object Loading : QueueUiState

    data class Success(val feed: List<QueueData>) : QueueUiState
}
