package com.example.solasave.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solasave.data.models.weatherdata.SolarCalculation
import com.example.solasave.data.repository.weather.WeatherService
import com.example.solasave.data.repository.weather.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SolarViewModel: ViewModel() {
    var cloudCover by mutableStateOf(0)
    var monthlyBill by mutableStateOf("5000")
    var selectedCounty by mutableStateOf("Nairobi")
    var factor by mutableStateOf(1.0)
    var isLoading by mutableStateOf(false)

    // savingsCalculator
    fun calculateSavings(): Double {
        val daily = (monthlyBill.toDoubleOrNull() ?: 0.0) / 30
        val efficiency = (100 - (cloudCover * 0.7)) / 100
        return daily * factor * efficiency
    }
    fun processAndSave() {
        viewModelScope.launch {
            isLoading = true
            try {
                // Fetch Weather
                val retrofit = Retrofit.Builder().baseUrl("https://openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                val service = retrofit.create(WeatherService::class.java)
                val weather = service.getClouds("${selectedCounty},KE","e3929e1962cf0c386149282b576f4ada")
                cloudCover = weather.clouds.all

                // Save to Supabase
                val result =
                    SolarCalculation(selectedCounty, monthlyBill.toDouble(), calculateSavings())
                supabase.from("calculations").insert(result)
            } catch (e: Exception) { println("Error: ${e.message}") }
            finally { isLoading = false }
        }
    }



}