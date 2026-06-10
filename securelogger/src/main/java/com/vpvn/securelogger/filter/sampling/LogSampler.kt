package com.vpvn.securelogger.filter.sampling

import com.vpvn.securelogger.model.LogEvent

class LogSampler(
    private val strategy: SamplingStrategy
) {

    fun allow(event: LogEvent): Boolean {
        return strategy.shouldLog(event)
    }
}