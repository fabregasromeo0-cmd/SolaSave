package com.example.solasave.data.models.weatherdata

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val clouds: CloudInfo
)
