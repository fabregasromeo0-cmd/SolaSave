package com.example.solasave.data.repository.weather

import com.example.solasave.data.models.weatherdata.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getClouds(@Query("q") q: String,
                          @Query("appid") key: String): WeatherResponse
}