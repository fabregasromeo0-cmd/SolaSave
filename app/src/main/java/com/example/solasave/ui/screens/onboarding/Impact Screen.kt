package com.example.solasave.ui.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.solasave.R
import com.example.solasave.ui.navigation.ROUTES
import com.example.solasave.ui.screens.home.SolarViewModel

@Composable
fun OnboardingScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(250.dp)
        )
        
        Spacer(Modifier.height(24.dp))
        
        Text(
            text = "SolaSave",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(Modifier.height(8.dp))
        
        Text(
            text = "Harness the power of the sun. Calculate your savings and reduce your carbon footprint effortlessly.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        
        Spacer(Modifier.height(64.dp))
        
        Button(
            onClick = { navController.navigate(ROUTES.Signup.name) },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Text("Get Started", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
        
        Spacer(Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { navController.navigate(ROUTES.Login.name) },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Text("Sign In", fontSize = 18.sp)
        }
    }
}

@Composable
fun ImpactScreen(navController: NavHostController, modifier: Modifier = Modifier, vm: SolarViewModel) {
    val savings = vm.calculateSavings() * 30 * 12 * 5
    val trees = (savings * 0.05).toInt()

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(40.dp))
        Text(
            text = "Environmental Impact",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(Modifier.padding(24.dp)) {
                Text("5-Year Potential Wealth", style = MaterialTheme.typography.titleMedium)
                Text(
                    "KSH ${String.format("%,.0f", savings)}",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(Modifier.height(24.dp))
        
        Text(
            text = "🌍 Switching to solar in ${vm.selectedCounty} is equivalent to planting $trees trees over 5 years.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
