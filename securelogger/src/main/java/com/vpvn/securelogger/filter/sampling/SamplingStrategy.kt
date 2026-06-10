package com.vpvn.securelogger.filter.sampling

import com.vpvn.securelogger.model.LogEvent

interface SamplingStrategy {
    fun shouldLog(event: LogEvent): Boolean
}