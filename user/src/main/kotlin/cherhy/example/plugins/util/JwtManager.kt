package cherhy.example.plugins.util

import cherhy.example.plugins.domain.Role
import cherhy.example.plugins.domain.UserId
import cherhy.example.plugins.domain.Username
import cherhy.example.plugins.util.constant.JwtProperty.AUDIENCE
import cherhy.example.plugins.util.constant.JwtProperty.EXPIRATION
import cherhy.example.plugins.util.constant.JwtProperty.ISSUER
import cherhy.example.plugins.util.constant.JwtProperty.SECRET
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.cherhy.common.util.ROLE
import com.cherhy.common.util.USERNAME
import com.cherhy.common.util.USER_ID
import mu.KotlinLogging
import java.util.*

class JwtManager {
    private val logger = KotlinLogging.logger {}

    fun createToken(
        userId: UserId,
        userName: Username,
        roles: List<Role>,
        tokenType: TokenType,
    ) =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim(USER_ID, userId.value)
            .withClaim(USERNAME, userName.value)
            .withClaim(ROLE, roles.joinToString { "," })
            .apply {
                when (tokenType) {
                    TokenType.ACCESS -> withExpiresAt(
                        Date(System.currentTimeMillis() + expiration)
                    )
                    TokenType.REFRESH -> withExpiresAt(
                        Date(System.currentTimeMillis() + expiration * 30)
                    )
                }
            }
            .sign(Algorithm.HMAC256(secret))!!

    companion object {
        @JvmStatic
        private val secret = ApplicationConfigUtils.getJwt(SECRET)

        @JvmStatic
        private val issuer = ApplicationConfigUtils.getJwt(ISSUER)

        @JvmStatic
        private val audience = ApplicationConfigUtils.getJwt(AUDIENCE)

        @JvmStatic
        private val expiration = ApplicationConfigUtils.getJwt(EXPIRATION).toLong()
    }
}