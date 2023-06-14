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
            .background(colorResource(id = R.color.Background)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Welcome to the Re:Deploy Firewall Manager!",
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = tokenIn,
                    onValueChange = { tokenIn = it },
                    singleLine = true,
                    label = { Text("PSK") },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
        }

        FloatingActionButton(
            onClick = {
                onLoginSubmit(
                    LoginRequest(
                        urlIn,
                        tokenIn
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
                text = "Submit",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }

}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(onLoginSubmit = {})
}