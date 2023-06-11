package com.redeploy.coreViewer.ui.screens

import FirewallEntry
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.redeploy.coreViewer.R

@Composable
fun HomeScreen(
    databaseUiState: List<FirewallEntry>,
    databaseTestUiState: String,
    onSyncClicked: () -> Unit,
    onFirewallSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.padding(start = 16.dp).padding(16.dp)) {
        var connectionDisplayColor: Color
        var displayTestUiState: String

        if (databaseTestUiState == "{\"message\":\"pong\"}") {
            displayTestUiState = "Database connected"
            connectionDisplayColor = Color.Green
        } else {
            displayTestUiState = "Database not connected"
            connectionDisplayColor = Color.Red
        }

        Text(
            text = displayTestUiState,
            color = connectionDisplayColor
        )

        Text(
            text = "Firewalls",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
    }

    ResultScreen(databaseTestUiState, databaseUiState, onSyncClicked, onFirewallSelected, modifier)
}


@Composable
fun ResultScreen(
    databaseTestUiState: String,
    databaseUiState: List<FirewallEntry>,
    onSyncClicked: () -> Unit,
    onFirewallSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(
                onClick = onSyncClicked,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Sync")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.padding(start = 16.dp).padding(16.dp)) {
                FirewallList(databaseUiState)
            }
        }
    }
}



@Composable
fun FirewallList(items: List<FirewallEntry>) {
    LazyColumn {
        items(items.size) { index ->
            val item = items[index]
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column() {
                    val imageModifier = Modifier
                        .size(100.dp)
                        .padding(20.dp)
                    Image(
                        painter = painterResource(R.drawable.firewall_icon),
                        contentDescription = "Firewall Icon",
                        contentScale = ContentScale.Crop,
                        modifier = imageModifier,
                    )
                }
                Column() {
                    Text(text = item.id)
                    Text(text = item.ip, textAlign = TextAlign.End, color = Color.LightGray)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 16.dp))
            }
        }
    }
}