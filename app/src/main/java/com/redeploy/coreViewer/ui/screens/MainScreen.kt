package com.redeploy.coreViewer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redeploy.coreViewer.network.GenericResponse
import com.redeploy.coreViewer.network.ListResponse
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
fun StatusSuccess(
    data: Response<GenericResponse>,
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
fun ListSuccess(
    data: Response<ListResponse>,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            var listData = data.body()?.msg
            items(listData!!) { item ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    ElevatedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        Text(
                            modifier = modifier
                                .padding(6.dp),
                            text = "ID: " + item.id
                        )
                        Text(
                            modifier = modifier
                                .padding(6.dp),
                            text = "VERSION: " + item.version.`product-version`
                        )
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.StatusSuccess -> StatusSuccess(uiState.response, modifier)
        is UiState.ListSuccess -> ListSuccess(uiState.response, modifier)
        is UiState.Loading -> Loading(modifier)
        is UiState.Error -> Error(modifier)
    }
}
