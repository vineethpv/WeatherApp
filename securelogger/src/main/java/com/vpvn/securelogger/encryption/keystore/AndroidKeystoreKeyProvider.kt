package com.vpvn.securelogger.encryption.keystore

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProperties.BLOCK_MODE_GCM
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class AndroidKeystoreKeyProvider(private val alias: String) : KeyProvider {

    override fun getOrCreateKey(): SecretKey {
        val ks = KeyStore.getInstance("AndroidKeyStore")

        ks.load(null)

        ks.getKey(alias, null)?.let {
            return it as SecretKey
        }

        val generator =
            KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")

        generator.init(
            KeyGenParameterSpec.Builder(alias, PURPOSE_ENCRYPT or PURPOSE_DECRYPT)
                .setBlockModes(
                    BLOCK_MODE_GCM
                )
                .setEncryptionPaddings(
                    ENCRYPTION_PADDING_NONE
                )
                .build()
        )

        return generator.generateKey()
    }
}