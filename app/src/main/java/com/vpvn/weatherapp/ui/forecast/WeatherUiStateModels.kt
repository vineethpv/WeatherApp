package com.vpvn.weatherapp.ui.forecast

import com.vpvn.weatherapp.domain.model.WeatherForecast

sealed interface WeatherIntent {
    object LoadWeather : WeatherIntent
    object PermissionGranted : WeatherIntent
    object PermissionDenied : WeatherIntent
    object LocationDisabled : WeatherIntent
}

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val forecast: List<WeatherForecast>) : WeatherUiState
    data class Error(val error: WeatherError) : WeatherUiState
}

sealed interface WeatherError {
    data object PermissionDenied : WeatherError
    data object LocationDisabled : WeatherError
    data object LocationUnavailable : WeatherError
    data class UnKnown(val message: String? = null) : WeatherError
}