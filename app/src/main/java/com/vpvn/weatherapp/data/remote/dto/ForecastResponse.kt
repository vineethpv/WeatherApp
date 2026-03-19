package com.vpvn.weatherapp.data.remote.dto

data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt_txt: String,
    val main: MainDto,
    val weather: List<WeatherDto>
)

data class MainDto(
    val temp: Double
)

data class WeatherDto(
    val main: String
)