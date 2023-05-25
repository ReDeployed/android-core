package com.redeploy.coreViewer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redeploy.coreViewer.network.LoginRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSubmit: (req: LoginRequest) -> Unit
) {
    var urlIn by remember { mutableStateOf("") }
    var tokenIn by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = urlIn,
                onValueChange = { urlIn = it },
                singleLine = true,
                label = { Text("API URL") }
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = tokenIn,
                onValueChange = { tokenIn = it },
                singleLine = true,
                label = { Text("PSK") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier
            .padding(40.dp),
        )
    }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        FloatingActionButton(
            onClick = { onLoginSubmit(
                LoginRequest(
                    urlIn,
                    tokenIn
                )
            ) },
            modifier = Modifier
                .padding(24.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Submit",
                fontSize = 16.sp
            )
        }
    }
}