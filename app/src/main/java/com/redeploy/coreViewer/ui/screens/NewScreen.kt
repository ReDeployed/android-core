package com.redeploy.coreViewer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redeploy.coreViewer.R
import com.redeploy.coreViewer.network.AddRequest
import com.redeploy.coreViewer.network.LoginRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewScreen(
    onAddSubmit: (req: AddRequest) -> Unit
) {
    var ipIn by remember { mutableStateOf("") }
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
            text = "Re:Deploy Core Viewer",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = ipIn,
                    onValueChange = { ipIn = it },
                    singleLine = true,
                    label = { Text("IP Address") }
                )
            }
        }
        FloatingActionButton(
            onClick = {
                onAddSubmit(
                    AddRequest (
                        ipIn
                    )
                ) },
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth(),
            containerColor = colorResource(id = R.color.Button),
            elevation = FloatingActionButtonDefaults.elevation(14.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Add",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Preview
@Composable
fun NewPreview() {
    NewScreen(onAddSubmit = {})
}