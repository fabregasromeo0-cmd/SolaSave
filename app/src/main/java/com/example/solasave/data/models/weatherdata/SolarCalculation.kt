package com.example.solasave.data.models.weatherdata

import kotlinx.serialization.Serializable

@Serializable
data class SolarCalculation(
    val county:String,
    val bill: Double,
    val savings: Double
)
