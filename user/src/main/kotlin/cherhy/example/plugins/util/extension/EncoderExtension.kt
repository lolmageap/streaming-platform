package cherhy.example.plugins.util.extension

import java.util.*

fun Base64.Encoder.encode(
    password: String,
) =
    this.encodeToString(password.toByteArray())!!

fun Base64.Encoder.matches(
    rawPassword: String,
    encodedPassword: String,
) {
    val isMatched = this.encodeToString(rawPassword.toByteArray()) != encodedPassword
    if (!isMatched) {
        throw IllegalArgumentException("Password is not matched")
    }
}