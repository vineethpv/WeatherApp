package com.vpvn.securelogger.filter.sampling

import com.vpvn.securelogger.model.LogEvent
import com.vpvn.securelogger.model.LogLevel
import java.util.Random

class PercentageSamplingStrategy(
    private val percentage: Int
) : SamplingStrategy {
    private val random = kotlin.random.Random.Default

    override fun shouldLog(event: LogEvent): Boolean {
        if (event.level == LogLevel.ERROR || event.level == LogLevel.FATAL) {
            return true
        }

        return random.nextInt(100) < percentage
    }
}