package com.vpvn.weatherapp.data.remote.repository

import com.vpvn.weatherapp.BuildConfig
import com.vpvn.weatherapp.data.mapper.WeatherMapper
import com.vpvn.weatherapp.data.remote.WeatherApi
import com.vpvn.weatherapp.domain.model.LocationCoordinates
import com.vpvn.weatherapp.domain.model.WeatherForecast
import com.vpvn.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getFiveDayForecast(locationCoordinates: LocationCoordinates): List<WeatherForecast> {
        val response = weatherApi.getFiveDayForecast(
            locationCoordinates.latitude,
            locationCoordinates.longitude,
            BuildConfig.WEATHER_API_KEY
        )

        return response.list
            .groupBy { it.dt_txt.substring(0, 10) }
            .map { it.value.first() }
            .map { WeatherMapper.mapToDomain(it) }
            .take(5)
    }

}