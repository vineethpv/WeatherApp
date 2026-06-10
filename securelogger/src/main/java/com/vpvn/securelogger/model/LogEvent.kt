package com.vpvn.securelogger.model

data class LogEvent(
    val timestamp: Long,
    val level: LogLevel,
    val tag: String,
    val message: String,
    val throwable: Throwable? = null,
    val metadata: Map<String, String> = emptyMap()
)
