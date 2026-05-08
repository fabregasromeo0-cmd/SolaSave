package com.example.solasave.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.solasave.ui.navigation.ROUTES

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: SolarViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(ROUTES.Calculator.name) },
                    icon = { Icon(Icons.Default.Add, null) },
                    label = {
                        Text(text = "Calc")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUTES.Impact.name) },
                    icon = { Icon(Icons.AutoMirrored.Filled.List, null) },
                    label = {
                        Text(text = "Impact")
                    }
                )
            }
        }
    ) { innerPadding ->
        // Use vm or innerPadding if needed, or just suppress it if the body is empty for now
        Text(text = "Home Content", modifier = Modifier.padding(innerPadding))
    }
}
