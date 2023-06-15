package com.redeploy.coreViewer.ui.screens

import android.graphics.Picture
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redeploy.coreViewer.R
import com.redeploy.coreViewer.network.GenericResponse
import com.redeploy.coreViewer.network.ListResponse
import com.redeploy.coreViewer.ui.UiState
import retrofit2.Response

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background))
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background))
    ) {
        Text(
            text = "Connection could not be established",
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Please try again",
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = ":(",
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            fontSize = 26.sp
        )
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
    entryNewAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Background)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Active Firewalls",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace
        )
        LazyColumn(
            modifier = Modifier
                .background(colorResource(id = R.color.Background)),
        ) {
            var listData = data.body()?.msg
            items(listData!!) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(colorResource(id = R.color.Background)),
                    ) {
                        Text(
                            modifier = modifier
                                .padding(6.dp),
                            text = "ID: " + item.id
                        )
                        Text(
                            modifier = modifier
                                .padding(6.dp),
                            text = item.version.`product-version`
                        )
                        Text(
                            modifier = modifier
                                .padding(6.dp),
                            text = item.version.`os-kernel-version`
                        )
                        Text(
                            modifier = modifier
                                .padding(6.dp),
                            text = item.version.`os-edition`
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { entryNewAction() },
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            containerColor = colorResource(id = R.color.Button),
            elevation = FloatingActionButtonDefaults.elevation(14.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "New Device",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun MainScreen(
    uiState: UiState,
    modifier: Modifier = Modifier,
    entryNewAction: () -> Unit,
) {
    when (uiState) {
        is UiState.StatusSuccess -> StatusSuccess(uiState.response, modifier)
        is UiState.ListSuccess -> ListSuccess(uiState.response, entryNewAction, modifier)
        is UiState.Loading -> Loading(modifier)
        is UiState.Error -> Error(modifier)
    }
}


