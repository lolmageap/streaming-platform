package cherhy.example.util.extension

import cherhy.example.util.PathVariable
import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.toUserId
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
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

val Headers.userId
    get() = this[USER_ID]?.toLongOrNull()?.toUserId()
        ?: throw IllegalArgumentException("$USER_ID header is required")