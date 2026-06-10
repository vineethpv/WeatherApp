package com.vpvn.securelogger.sink

import com.vpvn.securelogger.encryption.EncryptionProvider
import com.vpvn.securelogger.logrotation.LogRotator
import com.vpvn.securelogger.model.LogEvent

class EncryptedFileSink(
    private val logRotator: LogRotator,
    private val encryptionProvider: EncryptionProvider
) : LogSink {

    override fun write(event: LogEvent) {
        val encrypted = encryptionProvider.encrypt(event.message)
        val contentBytes = encrypted.toByteArray()
        logRotator.rotateIfNeeded(contentBytes.size.toLong())
        logRotator.activeFile().appendText(encrypted + "\n")

        //TODO remove decryption, added for testing
        val decrypted = encryptionProvider.decrypt(encrypted)
        logRotator.activeFile().appendText(decrypted + "\n")
    }
}