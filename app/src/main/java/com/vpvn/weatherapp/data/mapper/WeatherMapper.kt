package com.vpvn.weatherapp.data.mapper

import com.vpvn.weatherapp.data.remote.dto.ForecastItem
import com.vpvn.weatherapp.domain.model.WeatherForecast
import com.vpvn.weatherapp.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object WeatherMapper {
    fun mapToDomain(item: ForecastItem): WeatherForecast {
        return WeatherForecast(
            date = dateToDay(item.dt_txt),
            temperature = item.main.temp.toInt(),
            weatherType = mapWeather(item.weather.first().main),
            iconCode = item.weather.first().icon
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

    private fun dateToDay(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(date, formatter)

        val dayName = dateTime.dayOfWeek.getDisplayName(
            java.time.format.TextStyle.FULL,
            Locale.getDefault()
        )
        return dayName
    }
}