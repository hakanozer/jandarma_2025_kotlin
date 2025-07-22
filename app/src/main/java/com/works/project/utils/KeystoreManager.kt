package com.works.project.utils

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class KeystoreManager(private val context: Context) {

    private val KEYSTORE_ALIAS = "MyJwtKeyAlias"
    private val ANDROID_KEYSTORE = "AndroidKeyStore"

    private fun getKeyStore(): KeyStore {
        return KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
    }

    private fun generateOrGetSecretKey(): SecretKey {
        val keyStore = getKeyStore()
        if (!keyStore.containsAlias(KEYSTORE_ALIAS)) {
            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEYSTORE
            )
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    KEYSTORE_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
            return keyGenerator.generateKey()
        }
        return keyStore.getKey(KEYSTORE_ALIAS, null) as SecretKey
    }

    fun encryptData(data: String): Pair<String, String> { // Pair of (EncryptedData, IV)
        val secretKey = generateOrGetSecretKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val iv = cipher.iv // Initialization Vector
        val encryptedBytes = cipher.doFinal(data.toByteArray(Charsets.UTF_8))

        // IV ve şifreli veriyi Base64 ile encode et
        val ivString = Base64.encodeToString(iv, Base64.NO_WRAP)
        val encryptedDataString = Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)

        return Pair(encryptedDataString, ivString)
    }

    fun decryptData(encryptedData: String, iv: String): String? {
        try {
            val secretKey = generateOrGetSecretKey()
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")

            val ivBytes = Base64.decode(iv, Base64.NO_WRAP)
            val encryptedBytes = Base64.decode(encryptedData, Base64.NO_WRAP)

            val spec = GCMParameterSpec(128, ivBytes) // 128 bit tag length
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

            val decryptedBytes = cipher.doFinal(encryptedBytes)
            return String(decryptedBytes, Charsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
            // Hata yönetimi (örneğin, geçersiz IV veya anahtar)
            return null
        }
    }

    fun deleteJwt() {
        try {
            val keyStore = getKeyStore()
            if (keyStore.containsAlias(KEYSTORE_ALIAS)) {
                keyStore.deleteEntry(KEYSTORE_ALIAS)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}