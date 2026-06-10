package com.vpvn.securelogger.api

import com.vpvn.securelogger.buffer.LogBuffer
import com.vpvn.securelogger.enricher.LogEnricher
import com.vpvn.securelogger.filter.LogFilter
import com.vpvn.securelogger.filter.sampling.DefaultSamplingStrategy
import com.vpvn.securelogger.filter.sampling.LogSampler
import com.vpvn.securelogger.filter.sampling.SamplingStrategy
import com.vpvn.securelogger.formatter.JsonFormatter
import com.vpvn.securelogger.formatter.LogFormatter
import com.vpvn.securelogger.internal.LogDispatcher
import com.vpvn.securelogger.internal.LogPipeline
import com.vpvn.securelogger.model.LogLevel
import com.vpvn.securelogger.sink.LogSink
import com.vpvn.securelogger.tamperdetector.TamperDetector

class LoggerBuilder {

    private val sinks = mutableListOf<LogSink>()
    private val filters = mutableListOf<LogFilter>()
    private val enrichers = mutableListOf<LogEnricher>()
    private var formatter: LogFormatter = JsonFormatter()
    private var samplingStrategy: SamplingStrategy = DefaultSamplingStrategy()

    private var minLevel = LogLevel.INFO

    fun addSink(sink: LogSink) = apply { sinks.add(sink) }

    fun addFilter(filter: LogFilter) = apply { filters.add(filter) }

    fun addEnricher(enricher: LogEnricher) = apply { enrichers.add(enricher) }

    fun addFormatter(formatter: LogFormatter) = apply { this.formatter = formatter }

    fun samplingStrategy(strategy: SamplingStrategy) = apply { this.samplingStrategy = strategy }

    fun minLevel(level: LogLevel) = apply { minLevel = level }

    fun build(): SecureLogger {
        return SecureLogger(
            pipeline = LogPipeline(filters, enrichers, formatter),
            dispatcher = LogDispatcher(sinks, buffer = LogBuffer()),
            logSampler = LogSampler(strategy = samplingStrategy),
            tamperDetector = TamperDetector(),
            minLevel = minLevel
        )
    }
}