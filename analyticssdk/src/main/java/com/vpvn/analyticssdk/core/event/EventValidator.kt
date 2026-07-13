package com.vpvn.analyticssdk.core.event

class EventValidator(
    private val registry: EventRegistry
) {

    fun validate(event: Event): Boolean {

        val schema = registry.getSchema(event.eventName, event.schemaVersion) ?: return false

        return schema.requiredFields.all { event.properties.containsKey(it) }
    }
}