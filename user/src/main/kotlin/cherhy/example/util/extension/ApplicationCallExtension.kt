package cherhy.example.util.extension

import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.toUserId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.nio.file.AccessDeniedException

val ApplicationCall.jwt
    get() = this.principal<JWTPrincipal>()
        ?: throw AccessDeniedException("Invalid token")

val Headers.userId
    get() = this[USER_ID]?.toLongOrNull()?.toUserId()
        ?: throw IllegalArgumentException("$USER_ID header is required")