package com.mihaltsov.neo.feature.checkin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaltsov.neo.core.common.result.Result
import com.mihaltsov.neo.core.common.result.asResult
import com.mihaltsov.neo.core.domain.GetCheckInQrCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val TEXT_VALUE = "TEXT_VALUE"

@HiltViewModel
class CheckInViewModel @Inject constructor(
    checkInQrCodeUseCase: GetCheckInQrCodeUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val inputTextFieldValue: StateFlow<String> =
        savedStateHandle.getStateFlow(key = TEXT_VALUE, initialValue = "")

    val checkInUiState: StateFlow<CheckInUiState> =
        inputTextFieldValue
            .flatMapConcat { checkInQrCodeUseCase(it) }
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> CheckInUiState.Success(
                        qrModel = result.data,
                    )

                    is Result.Loading -> CheckInUiState.Loading
                    is Result.Error -> CheckInUiState.LoadFailed
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CheckInUiState.Loading,
            )

    fun onTextFieldChanged(text: String) {
        savedStateHandle[TEXT_VALUE] = text
    }
}