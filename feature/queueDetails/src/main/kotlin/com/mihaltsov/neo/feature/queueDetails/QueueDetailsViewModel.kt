package com.mihaltsov.neo.feature.queueDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.common.network.Dispatcher
import com.mihaltsov.neo.core.common.network.NeoDispatchers
import com.mihaltsov.neo.core.domain.ApplyToQueueUseCase
import com.mihaltsov.neo.core.domain.QueueUseCase
import com.mihaltsov.neo.feature.queueDetails.navigation.QueueArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueueDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    queueUseCase: QueueUseCase,
    private val applyToQueueUseCase: ApplyToQueueUseCase,
    @Dispatcher(NeoDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val queueArgs: QueueArgs = QueueArgs(savedStateHandle)
    private val queueId = queueArgs.queueId

    init {
        viewModelScope.launch(ioDispatcher) {
            queueUseCase.getQueueDetails(queueId)
        }
    }

    val queueData: StateFlow<QueueDetailsUiState> =
        queueUseCase().map { data ->
            if (data.persons.isEmpty()) {
                QueueDetailsUiState.Loading
            } else {
                QueueDetailsUiState.Success(data)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QueueDetailsUiState.Loading
        )

    fun applyToQueue() {
        viewModelScope.launch(ioDispatcher) {
            applyToQueueUseCase.applyToQueue(queueId)
        }
    }
}