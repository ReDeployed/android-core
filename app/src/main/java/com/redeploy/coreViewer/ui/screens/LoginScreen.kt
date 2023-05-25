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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = tokenIn,
                onValueChange = { tokenIn = it },
                singleLine = true,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
        }
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