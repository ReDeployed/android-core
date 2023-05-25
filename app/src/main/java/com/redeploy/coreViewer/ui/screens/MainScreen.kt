package com.redeploy.coreViewer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.redeploy.coreViewer.network.ApiResponse
import com.redeploy.coreViewer.ui.UiState
import retrofit2.Response

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
fun Success(
    data: Response<ApiResponse>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (data.isSuccessful) {
                Text(text = data.body()!!.type)
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (data.isSuccessful) {
                Text(text = data.body()!!.msg)
            }
        }
        Spacer(
            modifier = modifier
                .padding(40.dp),
        )
    }
}

@Composable
fun MainScreen(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Success -> Success(uiState.response, modifier)
        is UiState.Loading -> Loading(modifier)
        is UiState.Error -> Error(modifier)
    }
}
