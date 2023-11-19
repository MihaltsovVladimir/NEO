package com.mihaltsov.neo

import androidx.lifecycle.ViewModel
import com.mihaltsov.core.data.repository.TestHilt
import com.mihaltsov.core.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val userDataRepository: TestHilt,
) : ViewModel() {

    val dd = userDataRepository.text()

//    val uiState: StateFlow<MainActivityUiState> = userDataRepository.userData.map {
//        Success(it)
//    }.stateIn(
//        scope = viewModelScope,
//        initialValue = Loading,
//        started = SharingStarted.WhileSubscribed(5_000),
//    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}