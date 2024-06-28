package com.cherhy.payment.config

import com.cherhy.common.util.EndPoint.Payment.PAYMENT_DOMAIN
import com.cherhy.common.util.EndPoint.Stream.STREAM_DOMAIN
import com.cherhy.common.util.EndPoint.User.USER_DOMAIN
import com.cherhy.payment.jwt.JwtAuthenticationGlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfig(
    private val jwtAuthenticationGlobalFilter: JwtAuthenticationGlobalFilter,
) {
    @Bean
    fun serverHttpSecurity(): ServerHttpSecurity {
        return ServerHttpSecurity.http()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity,
    ) =
        http
            .addFilterBefore(jwtAuthenticationGlobalFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange { userDomain ->
                userDomain.pathMatchers(USER_DOMAIN).permitAll()
                userDomain.pathMatchers(HttpMethod.PUT, USER_DOMAIN).authenticated()
                userDomain.pathMatchers(HttpMethod.DELETE, USER_DOMAIN).authenticated()
                userDomain.anyExchange().authenticated()
            }
            .authorizeExchange { paymentDomain ->
                paymentDomain.pathMatchers(PAYMENT_DOMAIN).authenticated()
            }
            .authorizeExchange { streamDomain ->
                streamDomain.pathMatchers(STREAM_DOMAIN).authenticated()
            }
            .csrf { it.disable() }
            .build()!!
}