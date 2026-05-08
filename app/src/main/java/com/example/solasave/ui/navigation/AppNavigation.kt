package com.example.solasave.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.solasave.ui.screens.authentication.ForgotPasswordScreen
import com.example.solasave.ui.screens.authentication.LoginScreen
import com.example.solasave.ui.screens.authentication.SignupScreen
import com.example.solasave.ui.screens.home.HomeScreen
import com.example.solasave.ui.screens.onboarding.CalculatorScreen
import com.example.solasave.ui.screens.onboarding.ImpactScreen
import com.example.solasave.ui.screens.onboarding.OnboardingScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ROUTES.Onboarding.name,
        modifier = modifier
    ) {
        composable(ROUTES.Onboarding.name) { OnboardingScreen(navController, modifier) }
        composable(ROUTES.Login.name) { LoginScreen(navController, modifier) }
        composable(ROUTES.ForgotPassword.name) { ForgotPasswordScreen(navController, modifier) }
        composable(ROUTES.Home.name) { HomeScreen(navController, modifier) }
        composable(ROUTES.Signup.name) { SignupScreen(navController, modifier) }
        composable(ROUTES.Calculator.name) { CalculatorScreen(navController, modifier) }
        composable(ROUTES.Impact.name) { ImpactScreen(navController, modifier) }
    }
}
