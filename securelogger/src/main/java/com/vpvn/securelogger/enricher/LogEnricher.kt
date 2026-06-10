package com.vpvn.securelogger.enricher

import com.vpvn.securelogger.model.LogEvent

interface LogEnricher {
    fun enrich(event: LogEvent): LogEvent
}