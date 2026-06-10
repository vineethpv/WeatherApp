package com.vpvn.securelogger.filter.sampling

import com.vpvn.securelogger.model.LogEvent

class DefaultSamplingStrategy : SamplingStrategy {

    override fun shouldLog(event: LogEvent) = true
}