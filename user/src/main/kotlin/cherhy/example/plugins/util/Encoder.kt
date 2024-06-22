package cherhy.example.plugins.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

object Encoder {
    fun encodeBase64(value: String) = Base64.getEncoder().encodeToString(value.toByteArray())!!
    fun decodeBase64(value: String) = String(Base64.getDecoder().decode(value))
    fun matchesBase64(value: String, encoded: String) = decodeBase64(encoded) == value
    fun encodeBCrypt(value: String) = BCryptPasswordEncoder().encode(value)!!
    fun matchesBCrypt(value: String, encoded: String) = BCryptPasswordEncoder().matches(value, encoded)
}