package com.redeploy.coreViewer.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.redeploy.coreViewer.coreScreens
import com.redeploy.coreViewer.network.ApiResponse
import com.redeploy.coreViewer.network.LoginRequest
import com.redeploy.coreViewer.network.MainApi
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface UiState {
    data class Success(val response: Response<ApiResponse>) : UiState
    object Error : UiState
    object Loading : UiState
}

class MainViewModel : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
    private val api = MainApi.apiServer
    private var apiURL: String by mutableStateOf("")
    private var accessToken: String by mutableStateOf("")

    fun doLogin(nav: NavController, req: LoginRequest) {
        viewModelScope.launch {
            try {
                uiState = UiState.Loading
                nav.navigate(coreScreens.Start.name)
                val result = api.login(
                    "${req.url}api/auth",
                    req
                )
                accessToken = result.body()?.msg.toString()
                apiURL = req.url
                getStatus()
            } catch (e: Throwable) {
                UiState.Error
            }
        }
    }
    private fun getStatus() {
        viewModelScope.launch {
            uiState = try {
                val resultDevices = api.getStatus(
                    "${apiURL}api/status",
                    accessToken
                )
                UiState.Success(resultDevices)
            } catch (e: Throwable) {
                UiState.Error
            }
        }
    }
}
