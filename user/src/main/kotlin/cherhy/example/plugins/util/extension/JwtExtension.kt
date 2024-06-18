package cherhy.example.plugins.util.extension

import cherhy.example.plugins.domain.user.UserId
import cherhy.example.plugins.domain.user.UserName
import cherhy.example.plugins.util.property.SecurityProperty.USER_ID
import cherhy.example.plugins.util.property.SecurityProperty.USER_NAME
import io.ktor.server.auth.jwt.*

val JWTPrincipal?.customerName: UserName
    get() = this?.payload?.getClaim(USER_NAME)?.asString()?.let(UserName::of)
        ?: throw IllegalArgumentException("Invalid customer name")

val JWTPrincipal?.customerId: UserId
    get() = this?.payload?.getClaim(USER_ID)?.asLong()?.let(UserId::of)
        ?: throw IllegalArgumentException("Invalid user id")

val JWTPrincipal?.isExpired: Boolean
    get() = this?.expiresAt?.time?.let { it < System.currentTimeMillis() }
        ?: throw IllegalArgumentException("Invalid expire time")

val JWTPrincipal?.expireTime: Long
    get() = this?.expiresAt?.time?.minus(System.currentTimeMillis())
        ?: throw IllegalArgumentException("Invalid expire time")
