package com.vpvn.securelogger.enricher

import com.vpvn.securelogger.model.LogEvent

class SessionEnricher : LogEnricher {
    override fun enrich(event: LogEvent): LogEvent {

        return event.copy(
            metadata = event.metadata +
                    ("sessionId" to "sessionProvider.id()")
        )
    }
}