package com.vpvn.weatherapp.domain.repository

import com.vpvn.weatherapp.domain.model.LocationCoordinates
import com.vpvn.weatherapp.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getFiveDayForecast(locationCoordinates: LocationCoordinates): List<WeatherForecast>
}