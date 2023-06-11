package com.redeploy.coreViewer.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.redeploy.coreViewer.R
import com.redeploy.coreViewer.ui.screens.DatabaseViewModel
import com.redeploy.coreViewer.ui.screens.FirewallDetailScreen
import com.redeploy.coreViewer.ui.screens.HomeScreen


@Composable
fun DatabaseApiApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val databaseViewModel: DatabaseViewModel = viewModel()
            val navController = rememberNavController()
            val databaseUiState = databaseViewModel.databaseUiState
            val databaseTestUiState = databaseViewModel.databaseTestUiState

            NavHost(navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        databaseUiState = databaseUiState,
                        databaseTestUiState = databaseTestUiState,
                        onSyncClicked = { databaseViewModel.listApp() },
                        onFirewallSelected = { firewallId ->
                            navController.navigate("firewall/$firewallId")
                        }
                    )
                }
                composable(
                    "firewall/{firewallId}",
                    arguments = listOf(navArgument("firewallId") { type = NavType.StringType })
                ) { navBackStackEntry ->
                    val firewallId = navBackStackEntry.arguments?.getString("firewallId")
                    firewallId?.let { id ->
                        val firewall = databaseUiState.firstOrNull { it.id == id }
                        firewall?.let {
                            FirewallDetailScreen(firewall = it)
                        }
                    }
                }
            }
        }
    }
}
