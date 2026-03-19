package com.vpvn.weatherapp.ui.forecast

sealed interface WeatherIntent {
    object LoadWeather: WeatherIntent
    object PermissionGranted: WeatherIntent
    object PermissionDenied: WeatherIntent
    object LocationDisabled: WeatherIntent
}