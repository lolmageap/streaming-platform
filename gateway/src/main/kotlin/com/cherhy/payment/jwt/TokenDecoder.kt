package com.cherhy.payment.jwt

import com.cherhy.payment.util.model.Principal
import com.cherhy.payment.util.property.JwtProperty
import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.SignedJWT
import mu.KotlinLogging
import org.springframework.beans.factory.InitializingBean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class TokenDecoder(
    private val jwtProperty: JwtProperty,
) : InitializingBean {
    private val log = KotlinLogging.logger {}
    private lateinit var key: SecretKey
    override fun afterPropertiesSet() {
        this.key = SecretKeySpec(jwtProperty.secret.toByteArray(), jwtProperty.algorithm)
    }

    fun decode(
        token: String,
    ): UsernamePasswordAuthenticationToken {
        val claims = extractClaims(token)
        val userId = claims.subject.toLong()
        val roles = claims.getStringClaim("roles").split(",")
        val principal = Principal.of(userId, roles)
        val authorities = roles.map(::SimpleGrantedAuthority)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    private fun extractClaims(
        token: String,
    ) =
        try {
            val signedJWT = SignedJWT.parse(token)
            val verifier = MACVerifier(key)
            signedJWT.verify(verifier)
            signedJWT.jwtClaimsSet
        } catch (e: JOSEException) {
            log.info { "JWT 토큰이 만료 되었습니다. detail: $e" }
            error("JWT 토큰이 만료 되었습니다.")
        } catch (e: RuntimeException) {
            log.info { "JWT 토큰이 잘못 되었습니다. detail: $e" }
            error("JWT 토큰이 잘못 되었습니다.")
        }
}