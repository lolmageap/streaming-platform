package com.cherhy.payment.config

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
                userDomain.pathMatchers("/users/**").permitAll()
                userDomain.pathMatchers(HttpMethod.PUT, "/users/**").authenticated()
                userDomain.pathMatchers(HttpMethod.DELETE, "/users/**").authenticated()
                userDomain.anyExchange().authenticated()
            }
            .authorizeExchange { paymentDomain ->
                paymentDomain.pathMatchers("/payments/**").authenticated()
            }
            .authorizeExchange { streamDomain ->
                streamDomain.pathMatchers("/streams/**").authenticated()
            }
            .csrf { it.disable() }
            .build()!!
}