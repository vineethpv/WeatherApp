package com.vpvn.securelogger

import com.vpvn.securelogger.api.LoggerBuilder
import com.vpvn.securelogger.enricher.SessionEnricher
import com.vpvn.securelogger.filter.MetaDataSanitizerFilter
import com.vpvn.securelogger.filter.SensitiveDataFilter
import com.vpvn.securelogger.model.LogLevel
import com.vpvn.securelogger.sink.ConsoleSink
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun log_secureLogger() {
        val logger = LoggerBuilder()
            .addSink(ConsoleSink())
            .addFilter(SensitiveDataFilter())
            .addFilter((MetaDataSanitizerFilter()))
            .addEnricher(SessionEnricher())
            .minLevel(LogLevel.DEBUG)
            .build()

        logger.i(
            "Login",
            "User john@gmail.com authenticated with token eyJxxxx"
        )
    }
}