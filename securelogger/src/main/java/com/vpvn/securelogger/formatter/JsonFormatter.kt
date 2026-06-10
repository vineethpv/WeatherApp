package com.vpvn.securelogger.formatter

import com.vpvn.securelogger.model.LogEvent
import org.json.JSONObject

class JsonFormatter : LogFormatter {
    override fun format(event: LogEvent): LogEvent {
        val json =
            JSONObject()
                .put("ts", event.timestamp)
                .put("level", event.level.name)
                .put("tag", event.tag)
                .put("message", event.message)
                .toString()

        return event.copy(message = json)
    }
}