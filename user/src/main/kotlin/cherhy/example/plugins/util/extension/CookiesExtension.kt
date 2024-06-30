package cherhy.example.plugins.util.extension

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.date.*

var ResponseCookies.refreshToken: String?
    get() = get("Refresh-Token")?.value
    set(value) = append(
        name = "Refresh-Token",
        value = value ?: "",
        path = "/",
        expires = GMTDate(24 * 60 * 60 * 1000),
        httpOnly = true,
    )

val RequestCookies.refreshToken: String
    get() = get("Refresh-Token")
        ?: throw NoSuchElementException("refresh token is not found")