package com.vpvn.analyticssdk.core.event

import com.vpvn.analyticssdk.api.EventProvider
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRegistry @Inject constructor(
    providers: Set<@JvmSuppressWildcards EventProvider>
) {
    private val schemas = ConcurrentHashMap<String, EventSchema>()

    init {
        providers.forEach(::register)
    }

    //keeping this function for dynamic registration at runtime if needed, otherwise while startup hilt will provide all providers at once
    fun register(provider: EventProvider) {
        provider.schemas().forEach {
            schemas["${it.eventName}:${it.version}"] = it
        }
    }

    fun getSchema(eventName: String, version: Int): EventSchema? {
        return schemas["$eventName:$version"]
    }
}