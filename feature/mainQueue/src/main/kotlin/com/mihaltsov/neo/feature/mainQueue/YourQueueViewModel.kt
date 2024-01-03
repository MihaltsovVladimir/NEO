package com.mihaltsov.neo.feature.mainQueue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.domain.QueueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class YourQueueViewModel @Inject constructor(
    queueUseCase: QueueUseCase,
) : ViewModel() {

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