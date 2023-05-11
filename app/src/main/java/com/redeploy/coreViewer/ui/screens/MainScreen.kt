package com.redeploy.coreViewer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.redeploy.coreViewer.ui.UiState

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("gah, something failed >.<")
    }
}

@Composable
fun Result(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("this is, main")
    }
}

@Composable
fun MainScreen(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Loading -> Loading(modifier)
        is UiState.Success -> Result(modifier)
        is UiState.Error -> Error(modifier)
    }
}
