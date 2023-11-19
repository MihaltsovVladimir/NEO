package com.mihaltsov.feature.mainQueue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.core.data.repository.QueueDataRepository
import com.mihaltsov.core.model.QueueData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class YourQueueViewModel @Inject constructor(
    queueDataRepository: QueueDataRepository,
) : ViewModel() {

    private val _queueData: Flow<QueueData?> = queueDataRepository.queueData
    val queueData: StateFlow<QueueData?>
        get() = _queueData.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun removeItem(position: Int) {
        println(" position  $position")
    }
}