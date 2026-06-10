package com.vpvn.securelogger.internal

import android.util.Log
import com.vpvn.securelogger.buffer.LogBuffer
import com.vpvn.securelogger.model.LogEvent
import com.vpvn.securelogger.sink.LogSink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LogDispatcher(
    private val sinks: List<LogSink>,
    private val buffer: LogBuffer
) {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        scope.launch {
            for (event in buffer.stream()) {
                dispatchInternal(event)
            }
        }
    }

    private fun dispatchInternal(event: LogEvent) {
        sinks.forEach { sink ->
            try {
                sink.write(event)
            } catch (t: Throwable) {
                // isolate sink failures
                Log.e("LogDispatcher", "${t.message}")
            }
        }
    }

    fun dispatch(event: LogEvent) {
        buffer.tryEnqueue(event)
    }
}