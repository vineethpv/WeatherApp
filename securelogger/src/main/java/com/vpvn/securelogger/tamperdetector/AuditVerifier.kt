package com.vpvn.securelogger.tamperdetector

import com.vpvn.securelogger.model.AuditLogEvent

class AuditVerifier {

    fun verify(records: List<AuditLogEvent>): Boolean {
        var previous = "GENESIS"

        records.forEach {

            val recalculated =
                HashUtil.sha256(
                    "${it.event.timestamp}" +
                            it.event.level +
                            it.event.tag +
                            it.event.message +
                            previous
                )

            if (recalculated != it.currentHash) {
                return false
            }

            previous = it.currentHash
        }

        return true
    }
}