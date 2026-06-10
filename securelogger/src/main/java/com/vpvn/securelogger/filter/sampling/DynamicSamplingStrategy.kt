package com.vpvn.securelogger.filter.sampling

import com.vpvn.securelogger.model.LogEvent
import com.vpvn.securelogger.model.LogLevel
import java.util.Random

class DynamicSamplingStrategy : SamplingStrategy {

    private val random = kotlin.random.Random.Default

    override fun shouldLog(event: LogEvent): Boolean {

        val percentage = when (event.level) {
            LogLevel.VERBOSE -> 2
            LogLevel.DEBUG -> 50
            LogLevel.INFO -> 20
            LogLevel.WARN -> 100
            LogLevel.ERROR -> 100
            LogLevel.FATAL -> 100
        }

        return random.nextInt(100) < percentage
    }
}