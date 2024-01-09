package com.mihaltsov.neo.feature.queueDetails

import com.mihaltsov.neo.core.model.QueueData

sealed interface QueueDetailsUiState {

    data object Loading : QueueDetailsUiState

    data object LoadFailed : QueueDetailsUiState

    data class Success(val uiDataModel: QueueData = QueueData(persons = listOf())) : QueueDetailsUiState
}