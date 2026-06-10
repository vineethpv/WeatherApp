package com.vpvn.securelogger.filter

import com.vpvn.securelogger.model.LogEvent

class SensitiveDataFilter : LogFilter {
    private val emailRegex = Regex("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+")
    private val jwtRegex = Regex("eyJ[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+")
    private val cardRegex = Regex("\\b\\d{13,19}\\b")

    override fun process(event: LogEvent): LogEvent {
        var msg = event.message
        msg = emailRegex.replace(msg, "[EMAIL_REDACTED]")
        msg = jwtRegex.replace(msg, "[JWT_REDACTED]")
        msg = cardRegex.replace(msg, "[CARD_REDACTED]")

        return event.copy(message = msg)
    }
}