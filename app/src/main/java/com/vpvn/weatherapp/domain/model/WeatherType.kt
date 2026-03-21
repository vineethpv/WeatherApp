package com.vpvn.weatherapp.domain.model

import androidx.compose.ui.graphics.Color

enum class WeatherType {
    SUNNY, CLOUDY, RAINY;

    //TODO move color reference out of domain model
    fun gradient(): List<Color> {
        return when (this) {
            SUNNY -> SunnyGradient
            CLOUDY -> CloudySoftGradient
            RAINY -> RainyGradient
        }
    }
}

val SunnyGradient = listOf(
    Color(0xFF56CCF2), // light sky blue
    Color(0xFF2F80ED)  // deeper blue
)
val CloudySoftGradient = listOf(
    Color(0xFFD7DDE8),
    Color(0xFF757F9A)
)
val RainyGradient = listOf(
    Color(0xFF4B79A1), // steel blue
    Color(0xFF283E51)  // deep navy
)