package cherhy.example.util.extension

import cherhy.example.plugins.objectMapperKey
import cherhy.example.util.PathVariable
import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.toUserId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.nio.file.AccessDeniedException

val ApplicationCall.pathVariable
    get() = PathVariable(this)

val ApplicationCall.jwt
    get() = this.principal<JWTPrincipal>()
        ?: throw AccessDeniedException("Invalid token")

inline fun <reified T : Any> ApplicationCall.modelAttribute(): T {
    val objectmapper = application.attributes[objectMapperKey]

    val map =
        request.queryParameters.entries().associate { (key, values) ->
            if (values.isEmpty()) key to null
            else if (values.size == 1) key to values.first()
            else key to values
        }

    return objectmapper.convertValue(map, T::class.java)
}

val Headers.userId
    get() = this[USER_ID]?.toLongOrNull()?.toUserId()
        ?: throw IllegalArgumentException("$USER_ID header is required")