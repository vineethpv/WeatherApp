package com.vpvn.securelogger.model

data class AuditLogEvent(
    val event: LogEvent,
    val previousHash: String,
    val currentHash: String
)
