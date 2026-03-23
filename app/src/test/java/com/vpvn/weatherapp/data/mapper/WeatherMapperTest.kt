package com.vpvn.weatherapp.data.mapper

import com.vpvn.weatherapp.data.remote.dto.ForecastItem
import com.vpvn.weatherapp.data.remote.dto.MainDto
import com.vpvn.weatherapp.data.remote.dto.WeatherDto
import com.vpvn.weatherapp.domain.model.WeatherType
import org.junit.Test

class WeatherMapperTest {

    @Test
    fun `mapToDomain maps correctly`() {
        val item = ForecastItem(
            dt_txt = "2024-03-20 12:00:00",
            main = MainDto(temp = 30.5),
            weather = listOf(WeatherDto(main = "Clear", icon = "01d"))
        )

        val result = WeatherMapper.mapToDomain(item)

        assert(result.temperature == 30)
        assert(result.weatherType == WeatherType.SUNNY)
        assert(result.iconCode == "01d")
        assert(result.date.isNotEmpty())
    }

    @Test
    fun `mapWeather handles unknown type`() {
        val item = ForecastItem(
            dt_txt = "2024-03-20 12:00:00",
            main = MainDto(temp = 20.0),
            weather = listOf(WeatherDto(main = "Mist", icon = "50d"))
        )

        val result = WeatherMapper.mapToDomain(item)

        assert(result.weatherType == WeatherType.CLOUDY)
    }
}