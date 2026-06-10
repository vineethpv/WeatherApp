package com.vpvn.securelogger.internal

import com.vpvn.securelogger.enricher.LogEnricher
import com.vpvn.securelogger.filter.LogFilter
import com.vpvn.securelogger.formatter.LogFormatter
import com.vpvn.securelogger.model.LogEvent

class LogPipeline(
    private val filters: List<LogFilter>,
    private val enrichers: List<LogEnricher>,
    private val formatter: LogFormatter
) {

    fun execute(event: LogEvent): LogEvent {
        var current = event

        enrichers.forEach {
            current = it.enrich(current)
        }

        filters.forEach {
            current = it.process(current)
        }

        current = formatter.format(current)

        return current
    }
}