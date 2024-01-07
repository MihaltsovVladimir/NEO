package com.mihaltsov.neo.feature.mainQueue

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.domain.QueueUseCase
import com.mihaltsov.neo.feature.mainQueue.navigation.QueueArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class YourQueueViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    queueUseCase: QueueUseCase,
) : ViewModel() {

    private val queueArgs: QueueArgs = QueueArgs(savedStateHandle)

    val queueId = queueArgs.queueId

    val queueData: StateFlow<YourQueueUiState> =
        queueUseCase().map {
            if (it.persons.isEmpty()) {
                YourQueueUiState.LoadFailed
            } else {
                YourQueueUiState.Success(it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = YourQueueUiState.Loading
        )

    fun removeItem(position: Int) {
        println(" position  $position")
    }
}