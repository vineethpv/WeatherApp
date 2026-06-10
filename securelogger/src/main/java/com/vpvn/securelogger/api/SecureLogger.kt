package com.vpvn.securelogger.api

import com.vpvn.securelogger.filter.sampling.LogSampler
import com.vpvn.securelogger.internal.LogDispatcher
import com.vpvn.securelogger.internal.LogPipeline
import com.vpvn.securelogger.model.LogEvent
import com.vpvn.securelogger.model.LogLevel
import com.vpvn.securelogger.tamperdetector.TamperDetector

/*
    Feature additions :-
    Bounded queue (backpressure), Log rotation 10 MB × 10 files,
    Session correlation IDs, Request correlation IDs,
    Android Keystore key storage, TLS pinned network sink, Offline retry queue,
    Crash-safe flushing, signed log batches, exposing metaData.
 */
class SecureLogger internal constructor(
    private val pipeline: LogPipeline,
    private val dispatcher: LogDispatcher,
    private val logSampler: LogSampler,
    private val tamperDetector: TamperDetector,
    private val minLevel: LogLevel
) {

    fun log(
        logLevel: LogLevel,
        tag: String,
        message: String,
        metadata: Map<String, String> = emptyMap(),
        throwable: Throwable? = null
    ) {
        if (logLevel.ordinal < minLevel.ordinal) return

        val event = LogEvent(
            timestamp = System.currentTimeMillis(),
            level = logLevel,
            tag = tag,
            message = message,
            throwable = throwable,
            metadata = metadata
        )

        if (!logSampler.allow(event)) return

        val processedEvent = pipeline.execute(event)

        val auditEvent = tamperDetector.create(processedEvent)
        //AuditVerifier().verify() For verifying records

        dispatcher.dispatch(processedEvent)

    }

    fun d(tag: String, msg: String, metadata: Map<String, String> = emptyMap()) = log(LogLevel.DEBUG, tag = tag, message = msg, metadata = metadata)

    fun v(tag: String, msg: String, metadata: Map<String, String> = emptyMap()) = log(LogLevel.VERBOSE, tag = tag, message = msg, metadata = metadata)

    fun i(tag: String, msg: String, metadata: Map<String, String> = emptyMap()) = log(LogLevel.INFO, tag = tag, message = msg, metadata = metadata)

    fun w(tag: String, msg: String, metadata: Map<String, String> = emptyMap()) = log(LogLevel.WARN, tag = tag, message = msg, metadata = metadata)

    fun e(
        tag: String,
        msg: String,
        throwable: Throwable? = null,
        metadata: Map<String, String> = emptyMap()
    ) = log(
        LogLevel.ERROR,
        tag = tag,
        message = msg,
        metadata = metadata,
        throwable = throwable
    )
}