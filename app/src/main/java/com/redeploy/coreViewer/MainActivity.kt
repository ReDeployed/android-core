package com.redeploy.coreViewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.redeploy.coreViewer.network.ViewRequest
import com.redeploy.coreViewer.ui.theme.CoreViewerTheme
import com.redeploy.coreViewer.ui.MainViewModel
import com.redeploy.coreViewer.ui.UiState
import com.redeploy.coreViewer.ui.screens.LoginScreen
import com.redeploy.coreViewer.ui.screens.MainScreen
import com.redeploy.coreViewer.ui.screens.NewScreen
import com.redeploy.coreViewer.ui.screens.ViewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoreViewerTheme {
                DeviceManagerApp()
            }
        }
    }
}

enum class CoreScreens(val title: String) {
    Start(title = "Overview"),
    Login(title = "Login"),
    New(title = "New"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceManagerApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
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
                startDestination = CoreScreens.Login.name,
            ) {
                composable(route = CoreScreens.Start.name) {
                    MainScreen(
                        uiState = viewModel.uiState,
                        entryNewAction = {
                            navController.navigate(CoreScreens.New.name)
                        },
                        entryViewAction = {
                            viewModel.doView(ViewRequest(
                                it
                            ))
                        },
                        onBack = {
                            viewModel.getListing()
                        }
                    )
                }
                composable(route = CoreScreens.Login.name) {
                    LoginScreen(
                        onLoginSubmit = {
                            viewModel.doLogin(navController, it)
                        }
                    )
                }
                composable(route = CoreScreens.New.name) {
                    NewScreen(
                        onAddSubmit = {
                            viewModel.doAdd(navController, it)
                        }
                    )
                }
            }
        }
    }
}
