package com.vpvn.securelogger.filter

import com.vpvn.securelogger.model.LogEvent

class MetaDataSanitizerFilter : LogFilter {
    private val blockedKeys = setOf(
        "password",
        "token",
        "secret",
        "access_token",
        "refresh_token",
        "ssn"
    )

    override fun process(event: LogEvent): LogEvent {
        val sanitized = event.metadata.mapValues {
            if (it.key.lowercase() in blockedKeys)
                "***REDACTED***"
            else
                it.value
        }
        return event.copy(metadata = sanitized)
    }
}