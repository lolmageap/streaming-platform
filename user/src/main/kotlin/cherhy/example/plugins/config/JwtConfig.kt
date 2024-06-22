package cherhy.example.plugins.config

import cherhy.example.plugins.util.ApplicationConfigUtils.getJwt
import cherhy.example.plugins.util.property.JwtProperty.AUDIENCE
import cherhy.example.plugins.util.property.JwtProperty.EXPIRATION
import cherhy.example.plugins.util.property.JwtProperty.ISSUER
import cherhy.example.plugins.util.property.JwtProperty.REALM
import cherhy.example.plugins.util.property.JwtProperty.SECRET
import cherhy.example.plugins.util.property.SecurityProperty.AUTHORITY
import cherhy.example.plugins.util.property.SecurityProperty.USERNAME
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureJwt() {
    val jwtSecret = getJwt(SECRET)
    val jwtIssuer = getJwt(ISSUER)
    val jwtAudience = getJwt(AUDIENCE)
    val jwtRealm = getJwt(REALM)
    val expiration = getJwt(EXPIRATION).toLong()

    install(Authentication) {
        jwt(AUTHORITY) {
            realm = jwtRealm
            verifier(
                JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .acceptExpiresAt(expiration)
                    .withIssuer(jwtIssuer)
                    .build()
            )

            validate { credential ->
                if (credential.payload.getClaim(USERNAME).asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}