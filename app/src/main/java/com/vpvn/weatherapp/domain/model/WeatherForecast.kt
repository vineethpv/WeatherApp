package com.vpvn.weatherapp.domain.model

data class WeatherForecast(
    val date: String,
    val temperature: Double,
    val weatherType: WeatherType
)