package com.vpvn.analyticssdk.api

import com.vpvn.analyticssdk.core.event.EventSchema

interface EventProvider {
    fun schemas(): List<EventSchema>
}