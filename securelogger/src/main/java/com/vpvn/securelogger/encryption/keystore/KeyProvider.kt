package com.vpvn.securelogger.encryption.keystore

import javax.crypto.SecretKey

interface KeyProvider {
    fun getOrCreateKey(): SecretKey
}