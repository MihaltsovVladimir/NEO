package com.mihaltsov.neo.feature.queueDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.domain.QueueUseCase
import com.mihaltsov.neo.feature.queueDetails.navigation.QueueArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueueDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    queueUseCase: QueueUseCase,
) : ViewModel() {

    private val queueArgs: QueueArgs = QueueArgs(savedStateHandle)
    private val queueId = queueArgs.queueId

    init {
        viewModelScope.launch(Dispatchers.IO) {
            queueUseCase.getQueueDetails(queueId)
        }
    }

    val queueData: StateFlow<QueueDetailsUiState> =
        queueUseCase()
            .map {
                val filteredList = it.persons.filter { it.queueId == queueId }
                it.copy(persons = filteredList)
            }
            .map {
            if (it.persons.isEmpty()) {
                QueueDetailsUiState.LoadFailed
            } else {
                QueueDetailsUiState.Success(it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QueueDetailsUiState.Loading
        )

    fun removeItem(position: Int) {
        println(" position  $position")
    }
}