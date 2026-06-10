package com.vpvn.securelogger.filter

import com.vpvn.securelogger.model.LogEvent

interface LogFilter {
    fun process(event: LogEvent): LogEvent
}