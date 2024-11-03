package com.cherhy.gateway.config

import com.cherhy.common.util.Payment.PAYMENT_DOMAIN
import com.cherhy.common.util.Stream.STREAM_DOMAIN
import com.cherhy.common.util.User.DELETE_USER
import com.cherhy.common.util.User.UPDATE_USER
import com.cherhy.common.util.User.USER_DOMAIN
import com.cherhy.gateway.jwt.JwtAuthenticationGlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration

@Configuration
class SecurityConfig(
    private val jwtAuthenticationGlobalFilter: JwtAuthenticationGlobalFilter,
) {
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
                userDomain.pathMatchers(HttpMethod.PUT, UPDATE_USER).authenticated()
                userDomain.pathMatchers(HttpMethod.DELETE, DELETE_USER).authenticated()
                userDomain.pathMatchers(USER_DOMAIN).permitAll()
            }
            .authorizeExchange { paymentDomain ->
                paymentDomain.pathMatchers(PAYMENT_DOMAIN).authenticated()
            }
            .authorizeExchange { streamDomain ->
                streamDomain.pathMatchers(STREAM_DOMAIN).authenticated()
            }
            .csrf { it.disable() }
            .cors { it.configurationSource { corsConfigurationSource() } }
            .build()!!

    @Bean
    fun corsConfigurationSource() = CorsConfiguration().apply {
        allowCredentials = true
        allowedHeaders = listOf("*")
        allowedMethods = listOf("*")
        allowedOrigins = listOf("*")
    }
}