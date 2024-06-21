package com.cherhy.example.util.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperty(
    val secret: String,
    val expiration: Long,
    val refreshExpiration: Long,
    val algorithm: String,
    val authorityKey: String,
)