package com.vpvn.securelogger.buffer

import com.vpvn.securelogger.model.LogEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

class LogBuffer(capacity: Int = 5000) {

    private val channel =
        Channel<LogEvent>(capacity = capacity, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    suspend fun enqueue(event: LogEvent) {
        channel.send(event)
    }

    fun tryEnqueue(event: LogEvent): Boolean {
        return channel.trySend(event).isSuccess
    }

    fun stream(): ReceiveChannel<LogEvent> = channel
}