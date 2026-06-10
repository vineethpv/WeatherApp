package com.vpvn.securelogger.encryption

import android.util.Base64
import com.vpvn.securelogger.encryption.keystore.KeyProvider
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec

class AesGcmEncryptionProvider(private val keyProvider: KeyProvider) : EncryptionProvider {

    override fun encrypt(plaintText: String): String {

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, keyProvider.getOrCreateKey())
        val encrypted = cipher.doFinal(plaintText.toByteArray(Charsets.UTF_8))
        val result = cipher.iv + encrypted

        return Base64.encodeToString(result, Base64.NO_WRAP)
    }

    override fun decrypt(cipherText: String): String {

        val data = Base64.decode(cipherText, Base64.NO_WRAP)
        val iv = data.copyOfRange(0, 12)
        val payload = data.copyOfRange(12, data.size)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, keyProvider.getOrCreateKey(), GCMParameterSpec(128, iv))

        return String(cipher.doFinal(payload), Charsets.UTF_8)

    }

}