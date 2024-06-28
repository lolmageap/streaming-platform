package cherhy.example.plugins.util.extension

import cherhy.example.plugins.util.PathVariable
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.util.date.*
import java.nio.file.AccessDeniedException

val mapper = jacksonObjectMapper()

val ApplicationCall.pathVariable
    get() = PathVariable(this)

val ApplicationCall.jwt
    get() = this.principal<JWTPrincipal>()
        ?: throw AccessDeniedException("Invalid token")

inline fun <reified T : Any> ApplicationCall.getQueryParams(): T {
    return this.request.queryParameters.toClass()
}

inline fun <reified T : Any> Parameters.toClass(): T {
    val map = this.entries().associate {
        it.key to (it.value.getOrNull(0)
            ?: throw IllegalArgumentException("Missing value for key ${it.key}"))
    }
    return mapper.convertValue(map, T::class.java)
}

var ResponseHeaders.accessToken: String?
    get() = get("Authorization")
        ?: throw NoSuchElementException("access token is not found")
    set(value) = append("Authorization", value ?: "")

var ResponseCookies.refreshToken: String?
    get() = get("Refresh-Token")?.value
    set(value) = append(
        name = "Refresh-Token",
        value = value ?: "",
        path = "/",
        expires = GMTDate(24 * 60 * 60 * 1000),
        httpOnly = true,
    )