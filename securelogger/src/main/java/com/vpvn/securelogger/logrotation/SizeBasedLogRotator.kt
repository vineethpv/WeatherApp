package com.vpvn.securelogger.logrotation

import java.io.File
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class SizeBasedLogRotator(
    private val directory: File,
    private val fileName: String = "secure.log",
    private val maxSizeBytes: Long = 10L * 1024 * 1024,
    private val maxArchives: Int = 10
) : LogRotator {

    private val lock = ReentrantLock()

    override fun activeFile(): File {
        ensureFile()
        return File(directory, fileName)
    }

    override fun rotateIfNeeded(incomingBytes: Long) {
        lock.withLock {
            val active = activeFile()
            if (active.length() + incomingBytes <= maxSizeBytes) return

            rotate()
        }
    }

    private fun rotate() {

        File(directory, "$fileName.$maxArchives").delete()

        for (i in maxArchives - 1 downTo 1) {

            val src = File(directory, "$fileName.$i")

            if (!src.exists()) continue

            src.renameTo(File(directory, "$fileName.${i + 1}"))
        }

        activeFile().renameTo(File(directory, "$fileName.1"))

        activeFile().createNewFile()
    }

    private fun ensureFile() {
        directory.mkdirs()
        val file = File(directory, fileName)
        if (!file.exists()) file.createNewFile()
    }
}