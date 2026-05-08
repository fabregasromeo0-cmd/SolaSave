package com.example.solasave.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.solasave.ui.screens.home.SolarViewModel

@Composable
fun CalculatorScreen(navController: NavHostController, modifier: Modifier, vm: SolarViewModel = viewModel()
){

    Column(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Solar Advisor 🇰🇪", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = vm.monthlyBill, onValueChange = { vm.monthlyBill = it },
            label = {
                Text(
                    text = "Monthly KPLC Bill"
                )
            }, modifier = Modifier.fillMaxWidth())

        Text("County:")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.selectedCounty = "Nairobi"; vm.factor = 1.0; vm.processAndSave()
            }
            )
            { Text(
                text = "Nairobi") }
            Button(onClick = { vm.selectedCounty = "Garissa"; vm.factor = 1.4; vm.processAndSave() }) { Text("Garissa") }
        }

        if (vm.isLoading) CircularProgressIndicator()

        Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.Green)) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text="Daily Savings",
                    color = Color.White)
                Text(text = "KSH ${String.format("%.2f", vm.calculateSavings())}",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White)
            }
        }
    }
}