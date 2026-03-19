package com.vpvn.weatherapp.domain.usecase

import com.vpvn.weatherapp.domain.model.LocationCoordinates
import com.vpvn.weatherapp.domain.model.WeatherForecast
import com.vpvn.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetFiveDayForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        locationCoordinates: LocationCoordinates
    ): List<WeatherForecast> {
        return repository.getFiveDayForecast(locationCoordinates)
    }
}