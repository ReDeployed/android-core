package com.redeploy.coreViewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.redeploy.coreViewer.ui.DatabaseApiApp
import com.redeploy.coreViewer.ui.theme.CoreViewerTheme
import com.redeploy.coreViewer.ui.MainViewModel
import com.redeploy.coreViewer.ui.screens.LoginScreen
import com.redeploy.coreViewer.ui.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreViewerTheme {
                //DeviceManagerApp() <-- Amfoch hops gnumman
                DatabaseApiApp() // Da Da Da - Datenautobahn
            }
        }
    }
}

enum class coreScreens(val title: String) {
    Start(title = "Overview"),
    Login(title = "Login")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceManagerApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { CenterAlignedTopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel()
            NavHost(
                navController = navController,
                startDestination = coreScreens.Login.name,
            ) {
                composable(route = coreScreens.Start.name) {
                    MainScreen(
                        uiState = viewModel.uiState
                    )
                }
                composable(route = coreScreens.Login.name) {
                    LoginScreen(
                        onLoginSubmit = {
                            viewModel.doLogin(navController, it)
                        }
                    )
                }
            }
        }
    }
}
