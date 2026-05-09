package com.example.solasave.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.solasave.ui.screens.home.SolarViewModel

@Composable
fun CalculatorScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: SolarViewModel = viewModel()
) {
    val (dailyKplc, efficiency, total) = vm.getBreakdown()
    
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Calculation Breakdown", style = MaterialTheme.typography.titleLarge)
        
        OutlinedTextField(
            value = vm.monthlyBill,
            onValueChange = { vm.monthlyBill = it },
            label = { Text("Monthly Bill (KSH)") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Text("Select Timeframe: ${vm.timeframeDays.toInt()} Days")
        Slider(
            value = vm.timeframeDays,
            onValueChange = { vm.timeframeDays = it },
            valueRange = 1f..30f
        )
        
        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BreakdownRow("Base Daily Cost", "KSH ${String.format("%.2f", dailyKplc)}")
                BreakdownRow("Sun Efficiency", "${efficiency.toInt()}%")
                HorizontalDivider()
                BreakdownRow("Total Savings", "KSH ${String.format("%.2f", total)}", isBold = true)
            }
        }
        
        Button(
            onClick = { vm.saveGoalToSupabase(total) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Home, null)
            Spacer(Modifier.width(8.dp))
            Text("Save as My Goal")
        }
    }
}

@Composable
fun BreakdownRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Text(value, fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
    }
}
