package com.vpvn.securelogger.sink

import com.vpvn.securelogger.model.LogEvent
import java.io.File

class FileSink(private val file: File) : LogSink {
    private val lock = Any()

    override fun write(event: LogEvent) {
        synchronized(lock) {
            file.appendText("${event.timestamp} | ${event.level} | ${event.tag} | ${event.message}\n")
        }
    }
}