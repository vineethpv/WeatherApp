package com.vpvn.weatherapp.domain.model

data class WeatherForecast(
    val date: String,
    val temperature: Int,
    val weatherType: WeatherType,
    val iconCode: String
)