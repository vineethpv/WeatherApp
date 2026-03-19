package com.vpvn.weatherapp.data.mapper

import com.vpvn.weatherapp.data.remote.dto.ForecastItem
import com.vpvn.weatherapp.domain.model.WeatherForecast
import com.vpvn.weatherapp.domain.model.WeatherType

object WeatherMapper {
    fun mapToDomain(item: ForecastItem): WeatherForecast {
        return WeatherForecast(
            date = item.dt_txt,
            temperature = item.main.temp,
            weatherType = mapWeather(item.weather.first().main)
        )
    }

    private fun mapWeather(type: String): WeatherType {
        return when (type.lowercase()) {
            "clear" -> WeatherType.SUNNY
            "clouds" -> WeatherType.CLOUDY
            "rain" -> WeatherType.RAINY
            else -> WeatherType.CLOUDY
        }
    }
}