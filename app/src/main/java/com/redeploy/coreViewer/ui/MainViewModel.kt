package com.redeploy.coreViewer.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

sealed interface UiState {
    object Success : UiState
    object Error : UiState
    object Loading : UiState
}

class MainViewModel : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
}
