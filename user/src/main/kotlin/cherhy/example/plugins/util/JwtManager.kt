package cherhy.example.plugins.util

import cherhy.example.plugins.domain.user.UserId
import cherhy.example.plugins.domain.user.UserName
import cherhy.example.plugins.util.property.JwtProperty.AUDIENCE
import cherhy.example.plugins.util.property.JwtProperty.ISSUER
import cherhy.example.plugins.util.property.JwtProperty.SECRET
import cherhy.example.plugins.util.property.SecurityProperty.USER_ID
import cherhy.example.plugins.util.property.SecurityProperty.USER_NAME
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtManager {
    fun createToken(
        userId: UserId,
        userName: UserName,
    ) =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim(USER_ID, userId.value)
            .withClaim(USER_NAME, userName.value)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))!!

    companion object {
        @JvmStatic
        private val secret = ApplicationConfigUtils.getJwt(SECRET)

        @JvmStatic
        private val issuer = ApplicationConfigUtils.getJwt(ISSUER)

        @JvmStatic
        private val audience = ApplicationConfigUtils.getJwt(AUDIENCE)
    }
}