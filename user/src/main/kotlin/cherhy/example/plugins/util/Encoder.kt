package cherhy.example.plugins.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object Encoder {
    fun encodeBCrypt(
        value: String,
    ) = BCryptPasswordEncoder().encode(value)!!

    fun matchesBCrypt(
        value: String,
        encoded: String,
        block: () -> IllegalStateException = { throw IllegalStateException("Password does not match") }
    ) = BCryptPasswordEncoder().matches(value, encoded)
        .let { if (!it) block() }
}