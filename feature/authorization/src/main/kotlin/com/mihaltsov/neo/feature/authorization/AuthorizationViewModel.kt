package com.mihaltsov.neo.feature.authorization

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.data.repository.QueueDataRepository
import com.mihaltsov.neo.core.model.QueueData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val TEXT_VALUE = "searchQuery"

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    queueDataRepository: QueueDataRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val inputTextFieldValue: StateFlow<String> = savedStateHandle.getStateFlow(key = TEXT_VALUE, initialValue = "")

    private val _queueData: Flow<QueueData?> = queueDataRepository.queueData
    val queueData: StateFlow<QueueData?>
        get() = _queueData.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    private val _textField: Flow<String> = flow { "" }
    val textField: StateFlow<String>
        get() = _textField.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "Initial text"
        )

    fun onTextFieldChanged(text: String) {
        savedStateHandle[TEXT_VALUE] = text
    }
}