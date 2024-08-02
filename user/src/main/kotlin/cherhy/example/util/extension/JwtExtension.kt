package cherhy.example.util.extension

import cherhy.example.domain.Username
import com.cherhy.common.util.USERNAME
import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.UserId
import io.ktor.server.auth.jwt.*

val JWTPrincipal?.username: Username
    get() = this?.payload?.getClaim(USERNAME)?.asString()?.let(Username::of)
        ?: throw IllegalArgumentException("Invalid customer name")

val JWTPrincipal?.userId: UserId
    get() = this?.payload?.getClaim(USER_ID)?.asLong()?.let(UserId::of)
        ?: throw IllegalArgumentException("Invalid user id")

val JWTPrincipal?.isExpired: Boolean
    get() = this?.expiresAt?.time?.let { it < System.currentTimeMillis() }
        ?: throw IllegalArgumentException("Invalid expire time")

val JWTPrincipal?.expireTime: Long
    get() = this?.expiresAt?.time?.minus(System.currentTimeMillis())
        ?: throw IllegalArgumentException("Invalid expire time")