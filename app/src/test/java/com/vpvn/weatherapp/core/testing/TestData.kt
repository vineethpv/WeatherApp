package com.vpvn.weatherapp.core.testing

import com.vpvn.weatherapp.domain.model.LocationCoordinates
import com.vpvn.weatherapp.domain.model.WeatherForecast
import com.vpvn.weatherapp.domain.model.WeatherType

object TestData {

    val location = LocationCoordinates(10.0, 20.0)

    val forecastList = listOf(
        WeatherForecast("Monday", 25, WeatherType.SUNNY, "01d"),
        WeatherForecast("Tuesday", 22, WeatherType.CLOUDY, "02d")
    )
}