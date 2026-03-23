package com.vpvn.weatherapp.data.fake

import com.vpvn.weatherapp.data.remote.WeatherApi
import com.vpvn.weatherapp.data.remote.dto.ForecastItem
import com.vpvn.weatherapp.data.remote.dto.ForecastResponse
import com.vpvn.weatherapp.data.remote.dto.MainDto
import com.vpvn.weatherapp.data.remote.dto.WeatherDto

class FakeWeatherApi : WeatherApi {

    var shouldThrowError = false

    override suspend fun getFiveDayForecast(
        lat: Double,
        lon: Double,
        apiKey: String,
        units: String
    ): ForecastResponse {

        if (shouldThrowError) throw Exception("API error")

        return ForecastResponse(
            list = listOf(
                fakeItem("2024-03-20 12:00:00"),
                fakeItem("2024-03-21 12:00:00"),
                fakeItem("2024-03-22 12:00:00")
            )
        )
    }

    private fun fakeItem(date: String) = ForecastItem(
        dt_txt = date,
        main = MainDto(temp = 25.0),
        weather = listOf(WeatherDto("Clear", "01d"))
    )
}