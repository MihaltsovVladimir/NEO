package com.mihaltsov.neo.feature.checkin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.model.UserQrModel
import com.mihaltsov.neo.core.domain.GetCheckInQrCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val TEXT_VALUE = "TEXT_VALUE"

@HiltViewModel
class CheckInViewModel @Inject constructor(
    private val checkInQrCodeUseCase: GetCheckInQrCodeUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val inputTextFieldValue: StateFlow<String> =
        savedStateHandle.getStateFlow(key = TEXT_VALUE, initialValue = "")

    val checkInQrData: StateFlow<UserQrModel>
        get() = savedStateHandle.getStateFlow(key = TEXT_VALUE, initialValue = "")
            .flatMapLatest { textValue ->
                checkInQrCodeUseCase.invoke(textValue)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UserQrModel(""),
            )

    fun onTextFieldChanged(text: String) {
        savedStateHandle[TEXT_VALUE] = text
    }
}