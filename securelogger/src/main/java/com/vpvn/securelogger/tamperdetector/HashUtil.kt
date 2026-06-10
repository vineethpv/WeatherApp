package com.vpvn.securelogger.tamperdetector

import java.security.MessageDigest

object HashUtil {

    fun sha256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(input.toByteArray()).joinToString("") {
            "%02x".format(it)
        }
    }
}