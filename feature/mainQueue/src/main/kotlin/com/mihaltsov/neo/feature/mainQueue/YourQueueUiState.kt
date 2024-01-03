package com.mihaltsov.neo.feature.mainQueue

import com.mihaltsov.neo.core.model.QueueData

sealed interface YourQueueUiState {

    data object Loading : YourQueueUiState

    data object LoadFailed : YourQueueUiState

    data class Success(val uiDataModel: QueueData = QueueData(id = "", persons = listOf())) : YourQueueUiState
}