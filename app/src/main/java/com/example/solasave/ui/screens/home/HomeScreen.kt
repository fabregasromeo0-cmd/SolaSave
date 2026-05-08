package com.example.solasave.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.solasave.ui.navigation.ROUTES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: SolarViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text("Good Morning", style = MaterialTheme.typography.bodySmall)
                        Text("Eco Hero!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton({}) { Icon(Icons.Default.Notifications, null) }
                    IconButton({}) { Icon(Icons.Default.Person, null) }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(true, {}, { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                NavigationBarItem(false, { navController.navigate(ROUTES.Calculator.name) }, { Icon(Icons.Default.Add, null) }, label = { Text("Calculator") })
                NavigationBarItem(false, { navController.navigate(ROUTES.Impact.name) }, { Icon(Icons.AutoMirrored.Filled.List, null) }, label = { Text("Impact") })
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
            ) {
                Column(Modifier.padding(24.dp)) {
                    Text("Current Savings", color = MaterialTheme.colorScheme.onPrimary)
                    Text("KSH 12,450", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
                    Spacer(Modifier.height(8.dp))
                    Text("Total saved since January", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimary.copy(0.8f))
                }
            }

            Text("Quick Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                QuickActionItem("New Estimate", Icons.Default.Add, Modifier.weight(1f)) { navController.navigate(ROUTES.Calculator.name) }
                QuickActionItem("View Stats", Icons.AutoMirrored.Filled.List, Modifier.weight(1f)) { navController.navigate(ROUTES.Impact.name) }
            }
            
            Text("Tip of the Day", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(40.dp))
                    Spacer(Modifier.width(16.dp))
                    Text("Switching to LED bulbs can increase your solar efficiency ROI by 15%.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun QuickActionItem(title: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
            Spacer(Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.labelLarge)
        }
    }
}
