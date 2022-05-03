package dev.juanrincon.data.utilities

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


fun hash(password: String): String {
    val hashKey = hex(System.getenv("SECRET_KEY"))
    val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmacKey)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}