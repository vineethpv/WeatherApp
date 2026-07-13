package com.vpvn.weatherapp.analytics

import com.vpvn.analyticssdk.api.EventProvider
import com.vpvn.analyticssdk.core.event.EventSchema

class AnalyticsWeatherEventProvider : EventProvider {

    override fun schemas(): List<EventSchema> {
        return listOf(
            EventSchema(
                eventName = "weather_loaded",
                version = 1,
                requiredFields = setOf(
                    "city", "temperature"
                )
            )
        )
    }
}