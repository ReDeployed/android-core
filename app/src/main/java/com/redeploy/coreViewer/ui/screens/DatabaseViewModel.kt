package com.redeploy.coreViewer.ui.screens

import FirewallEntry
import FirewallManagerResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redeploy.coreViewer.network.DatabaseApi
import kotlinx.coroutines.launch
import com.google.gson.Gson
import java.io.IOException


class DatabaseViewModel : ViewModel() {

    var databaseTestUiState: String by mutableStateOf("")
        private set

    var databaseUiState: List<FirewallEntry> by mutableStateOf(emptyList())
        private set

    init {
        testDatabase()
        listApp()
    }

    private fun testDatabase() {
        viewModelScope.launch {
            try {
                val dbTestResult = DatabaseApi.retrofitService.testDB()
                databaseTestUiState = dbTestResult
            } catch (e: IOException) {
                println(e)
            }
        }
    }

    fun listApp() {
        viewModelScope.launch {
            try {
                val dbResult = DatabaseApi.retrofitService.listApp()

                val gson = Gson()
                val response: FirewallManagerResponse = gson.fromJson(dbResult, FirewallManagerResponse::class.java)
                val firewallEntryList: List<FirewallEntry> = response.toFirewallEntryList()

                databaseUiState = firewallEntryList

            } catch (e: IOException) {
                println(e)
            }
        }
    }
}