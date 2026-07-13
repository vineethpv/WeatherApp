package com.vpvn.weatherapp.analytics

import com.vpvn.analyticssdk.api.EventProvider
import com.vpvn.analyticssdk.core.event.EventSchema

class AnalyticsTestEventProvider : EventProvider {

    override fun schemas(): List<EventSchema> {
        return emptyList()//TODO
    }
}