package com.example.solasave.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.solasave.ui.navigation.ROUTES
import com.example.solasave.ui.screens.authentication.*
import com.example.solasave.ui.screens.onboarding.*

@Composable
fun SolaSaveApp(vm: SolarViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavScreens = listOf(ROUTES.Home.name, ROUTES.Calculator.name, ROUTES.Impact.name)
    val showBottomBar = currentRoute in bottomNavScreens

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    BottomNavItem(navController, ROUTES.Home.name, Icons.Default.Home, "Home", currentRoute)
                    BottomNavItem(navController, ROUTES.Calculator.name, Icons.Default.Add, "Calculator", currentRoute)
                    BottomNavItem(navController, ROUTES.Impact.name, Icons.Default.KeyboardArrowUp, "Impact", currentRoute)
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = ROUTES.Onboarding.name,
            modifier = Modifier.padding(padding)
        ) {
            composable(ROUTES.Onboarding.name) { OnboardingScreen(navController) }
            composable(ROUTES.Login.name) { LoginScreen(navController) }
            composable(ROUTES.Signup.name) { SignupScreen(navController) }
            composable(ROUTES.ForgotPassword.name) { ForgotPasswordScreen(navController) }
            composable(ROUTES.Home.name) { HomeScreen(navController, vm = vm) }
            composable(ROUTES.Calculator.name) { CalculatorScreen(navController, vm = vm) }
            composable(ROUTES.Impact.name) { ImpactScreen(navController, vm = vm) }
        }
    }
}

@Composable
private fun RowScope.BottomNavItem(
    navController: NavHostController,
    route: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    currentRoute: String?
) {
    NavigationBarItem(
        selected = currentRoute == route,
        onClick = {
            if (currentRoute != route) {
                navController.navigate(route) {
                    if (route == ROUTES.Home.name) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        },
        icon = { Icon(icon, null) },
        label = { Text(label) }
    )
}
