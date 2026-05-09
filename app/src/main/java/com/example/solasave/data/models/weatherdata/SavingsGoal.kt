package com.example.solasave.data.models.weatherdata

import kotlinx.serialization.Serializable

@Serializable
data class SavingsGoal(val goal_name:String,
    val target_amount:Double,
    val timeframe_days: Int)
