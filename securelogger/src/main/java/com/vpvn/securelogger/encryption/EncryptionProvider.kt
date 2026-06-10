package com.vpvn.securelogger.encryption

interface EncryptionProvider {

    fun encrypt(plaintText: String): String

    fun decrypt(cipherText: String): String
}