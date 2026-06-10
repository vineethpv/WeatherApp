package com.vpvn.securelogger.tamperdetector

import com.vpvn.securelogger.model.AuditLogEvent
import com.vpvn.securelogger.model.LogEvent

class TamperDetector {

    private var previousHash = "GENESIS"

    fun create(event: LogEvent): AuditLogEvent {

        val payload = "${event.timestamp}" + event.level + event.tag + event.message + previousHash
        val currentHash = HashUtil.sha256(payload)
        val auditLogEvent = AuditLogEvent(event, previousHash, currentHash)
        previousHash = currentHash

        return auditLogEvent
    }
}