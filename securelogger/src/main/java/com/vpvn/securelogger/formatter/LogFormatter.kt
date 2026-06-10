package com.vpvn.securelogger.formatter

import com.vpvn.securelogger.model.LogEvent

interface LogFormatter {
    fun format(event: LogEvent): LogEvent
}