package com.example.solasave.ui.screens.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solasave.BuildConfig
import com.example.solasave.data.models.weatherdata.SavingsGoal
import com.example.solasave.data.models.weatherdata.SolarCalculation
import com.example.solasave.data.repository.weather.WeatherService
import com.example.solasave.data.repository.weather.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SolarViewModel : ViewModel() {
    var cloudCover by mutableIntStateOf(0)
    var monthlyBill by mutableStateOf("5000")
    var selectedCounty by mutableStateOf("Nairobi")
    var factor by mutableDoubleStateOf(1.0)
    var timeframeDays by mutableFloatStateOf(30f)
    var isLoading by mutableStateOf(false)

    fun calculateSavings(): Double {
        val daily = (monthlyBill.toDoubleOrNull() ?: 0.0) / 30
        val efficiency = (100 - (cloudCover * 0.7)) / 100
        return daily * factor * efficiency
    }

    fun getBreakdown() = Triple(
        (monthlyBill.toDoubleOrNull() ?: 0.0) / 30,
        100 - (cloudCover * 0.7).toDouble(),
        calculateSavings() * timeframeDays.toDouble()
    )

    fun processAndSave() {
        viewModelScope.launch {
            isLoading = true
            try {
                val service = Retrofit.Builder()
                    .baseUrl("https://openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WeatherService::class.java)

                val weather = service.getClouds("$selectedCounty,KE", BuildConfig.WEATHER_API_KEY)
                cloudCover = weather.clouds.all

                val result = SolarCalculation(selectedCounty, monthlyBill.toDouble(), calculateSavings())
                supabase.from("calculations").insert(result)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun saveGoalToSupabase(amount: Double) {
        viewModelScope.launch {
            try {
                val goal = SavingsGoal("Solar Goal", amount, timeframeDays.toInt())
                supabase.from("savings_goals").insert(goal)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
