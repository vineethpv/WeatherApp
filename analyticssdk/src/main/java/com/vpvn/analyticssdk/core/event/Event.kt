package com.vpvn.analyticssdk.core.event

data class Event(
    val id: String,
    val eventName: String,
    val timestamp: Long,
    val userId: String?,
    val sessionId: String,
    val schemaVersion: Int,
    val properties: Map<String, Any>
)
