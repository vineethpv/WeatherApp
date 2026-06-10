package com.vpvn.securelogger.sink

import com.vpvn.securelogger.model.LogEvent

interface LogSink {
    fun write(event: LogEvent)
}