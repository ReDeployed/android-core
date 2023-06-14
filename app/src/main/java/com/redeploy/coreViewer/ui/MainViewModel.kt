package com.redeploy.coreViewer.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.redeploy.coreViewer.coreScreens
import com.redeploy.coreViewer.network.GenericResponse
import com.redeploy.coreViewer.network.ListResponse
import com.redeploy.coreViewer.network.LoginRequest
import com.redeploy.coreViewer.network.MainApi
import kotlinx.coroutines.launch
import retrofit2.Response

sealed interface UiState {
    data class StatusSuccess(val response: Response<GenericResponse>) : UiState
    data class ListSuccess(val response: Response<ListResponse>) : UiState
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
                val result = api.login(
                    "${req.url}api/auth",
                    req
                )
                accessToken = result.body()?.msg.toString()
                apiURL = req.url
                if (result.isSuccessful) {
                    nav.navigate(coreScreens.Start.name)
                    getListing()
                }
            } catch (e: Throwable) {
                UiState.Error
            }
        }
    }
    private fun getStatus() {
        viewModelScope.launch {
            uiState = try {
                val result = api.getStatus(
                    "${apiURL}api/status",
                    accessToken
                )
                if (result.isSuccessful) {
                    UiState.StatusSuccess(result)
                } else {
                    UiState.Error
                }
            } catch (e: Throwable) {
                UiState.Error
            }
        }
    }
    private fun getListing() {
        viewModelScope.launch {
            uiState = try {
                val resultDevices = api.getListing(
                    "${apiURL}api/listApp",
                    accessToken
                )
                if (resultDevices.isSuccessful) {
                    UiState.ListSuccess(resultDevices)
                } else {
                    UiState.Error
                }
            } catch (e: Throwable) {
                UiState.Error
            }
        }
    }
}
