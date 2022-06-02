package app.tracktion.data.utilities

import io.ktor.server.application.*
import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


fun hash(password: String, environment: ApplicationEnvironment): String {
    val hashKey = hex(environment.config.propertyOrNull("jwt.secret")?.getString() ?: "0")
    val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")
    val hmac = Mac.getInstance("HmacSHA1")
    hmac.init(hmacKey)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}