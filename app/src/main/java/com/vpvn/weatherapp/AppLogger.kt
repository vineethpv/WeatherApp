package com.vpvn.weatherapp

import android.content.Context
import com.vpvn.securelogger.api.LoggerBuilder
import com.vpvn.securelogger.encryption.AesGcmEncryptionProvider
import com.vpvn.securelogger.encryption.keystore.AndroidKeystoreKeyProvider
import com.vpvn.securelogger.enricher.SessionEnricher
import com.vpvn.securelogger.filter.MetaDataSanitizerFilter
import com.vpvn.securelogger.filter.SensitiveDataFilter
import com.vpvn.securelogger.filter.sampling.DynamicSamplingStrategy
import com.vpvn.securelogger.logrotation.SizeBasedLogRotator
import com.vpvn.securelogger.model.LogLevel
import com.vpvn.securelogger.sink.ConsoleSink
import com.vpvn.securelogger.sink.EncryptedFileSink
import java.io.File

//Enhancements : Instead of singleton use class LoggerFactory(private val context: Context) and inject via Hilt
object AppLogger {

    private const val KEY_ALIAS = "secure_logger_key"
    private lateinit var logDir: File

    val logger by lazy {
        LoggerBuilder()
            .addSink(ConsoleSink())
            .addSink(
                EncryptedFileSink(
                    logRotator = SizeBasedLogRotator(directory = logDir),
                    encryptionProvider = AesGcmEncryptionProvider(keyProvider = AndroidKeystoreKeyProvider(KEY_ALIAS))
                )
            )
            .addFilter(SensitiveDataFilter())
            .addFilter((MetaDataSanitizerFilter()))
            .addEnricher(SessionEnricher())
            .samplingStrategy(DynamicSamplingStrategy())
            .minLevel(LogLevel.DEBUG)
            .build()
    }

    fun init(context: Context) {
        logDir = File(context.filesDir, "logs").apply { mkdirs() }
    }
}