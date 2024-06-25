package cherhy.example.plugins.util.extension

import cherhy.example.plugins.domain.UserId
import cherhy.example.plugins.domain.Username
import cherhy.example.plugins.util.constant.SecurityProperty.USER_ID
import cherhy.example.plugins.util.constant.SecurityProperty.USERNAME
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