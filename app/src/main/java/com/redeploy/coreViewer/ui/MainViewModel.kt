package com.redeploy.coreViewer.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.redeploy.coreViewer.coreScreens
import com.redeploy.coreViewer.network.LoginRequest
import com.redeploy.coreViewer.network.MainApi
import kotlinx.coroutines.launch

sealed interface UiState {
    object Success : UiState
    object Error : UiState
    object Loading : UiState
}

class MainViewModel : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Success)
    private val api = MainApi.apiServer
    var userID: Int by mutableStateOf(-1)
    var accessToken: String by mutableStateOf("")

    fun doLogin(nav: NavController, req: LoginRequest) {
        var success = false
        viewModelScope.launch {
            try {
                uiState = UiState.Loading
                nav.navigate(coreScreens.Start.name)
                val result = api.login(
                    req
                )
                accessToken = result.body()?.msg.toString()
                success = true
            } catch (e: Throwable) {
                success = false
                nav.navigate(coreScreens.Login.name)
            }
        }
    }
}
