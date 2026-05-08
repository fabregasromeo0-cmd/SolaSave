package com.example.solasave.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.solasave.ui.screens.home.SolarViewModel

@Composable
fun ImpactScreen(navController: NavHostController, modifier: Modifier, vm: SolarViewModel = viewModel() ){
    val monthly = vm.calculateSavings() * 30
    Column(Modifier.padding(24.dp)) {
        Text(
            text = "Monthly Impact",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = "Total Savings: KSH ${monthly.toInt()}",
            Modifier.padding(top = 10.dp))
        Text(text = "🌳 Trees Equivalent: ${(monthly * 0.02).toInt()}"
            , color = Color.Green
        )
    }
}