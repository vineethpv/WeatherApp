package com.vpvn.securelogger.logrotation

import java.io.File

interface LogRotator {
    fun activeFile(): File
    fun rotateIfNeeded(incomingBytes: Long)
}