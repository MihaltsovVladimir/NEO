package com.mihaltsov.neo.feature.queues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.data.repository.QueuesRepository
import com.mihaltsov.neo.core.domain.QueueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QueuesViewModel @Inject constructor(
    repository: QueuesRepository,
) : ViewModel() {

    val queueData: StateFlow<QueuesUiState> =
        repository.existQueues.map {
            if (it.isEmpty()) {
                QueuesUiState.LoadFailed
            } else {
                QueuesUiState.Success(it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QueuesUiState.Loading
        )
}