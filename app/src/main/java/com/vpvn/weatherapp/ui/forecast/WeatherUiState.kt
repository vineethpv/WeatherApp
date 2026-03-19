package com.vpvn.weatherapp.ui.forecast

import com.vpvn.weatherapp.domain.model.WeatherForecast

sealed interface WeatherUiState {
    object Loading: WeatherUiState
    data class Success(val forecast: List<WeatherForecast>): WeatherUiState
    data class Error(val message: String): WeatherUiState
}