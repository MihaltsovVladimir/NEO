package com.mihaltsov.feature.mainQueue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.core.data.util.NetworkMonitor
import com.mihaltsov.core.model.QueueData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QueueViewModel @Inject constructor(
) : ViewModel() {


    private fun makeQueue(): QueueData {
        val list = mutableListOf<QueueData.PersonQueueData>()
        repeat(100) {
            list.add(
                QueueData.PersonQueueData(
                    nickName = "Mihaltsov",
                    queueNumber = it,
                    oldPosition = 0,
                    newPosition = 2
                )
            )
        }
        return QueueData(list)
    }


    private val _queueData: Flow<QueueData?> = flowOf(makeQueue())
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