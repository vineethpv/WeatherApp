package com.vpvn.securelogger.sink

import com.vpvn.securelogger.model.LogEvent
import com.vpvn.securelogger.model.LogLevel

class ConsoleSink : LogSink {

    override fun write(event: LogEvent) {
        when (event.level) {
            LogLevel.VERBOSE -> android.util.Log.v(event.tag, event.message)
            LogLevel.DEBUG -> android.util.Log.d(event.tag, event.message)
            LogLevel.INFO -> android.util.Log.i(event.tag, event.message)
            LogLevel.WARN -> android.util.Log.w(event.tag, event.message)
            LogLevel.ERROR,
            LogLevel.FATAL -> android.util.Log.e(event.tag, event.message, event.throwable)
        }
    }
}