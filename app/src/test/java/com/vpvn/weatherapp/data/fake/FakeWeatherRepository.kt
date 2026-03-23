package com.vpvn.weatherapp.data.fake

import com.vpvn.weatherapp.core.testing.TestData
import com.vpvn.weatherapp.domain.model.LocationCoordinates
import com.vpvn.weatherapp.domain.model.WeatherForecast
import com.vpvn.weatherapp.domain.repository.WeatherRepository

class FakeWeatherRepository : WeatherRepository {

    var shouldThrowError = false
    var fakeData: List<WeatherForecast> = TestData.forecastList

    override suspend fun getFiveDayForecast(
        locationCoordinates: LocationCoordinates
    ): List<WeatherForecast> {
        if (shouldThrowError) throw Exception("Fake error")
        return fakeData
    }
}