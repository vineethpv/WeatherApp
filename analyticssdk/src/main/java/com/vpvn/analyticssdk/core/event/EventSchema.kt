package com.vpvn.analyticssdk.core.event

data class EventSchema(
    val eventName: String,
    val version: Int,
    val requiredFields: Set<String>
)
