package com.cherhy.payment.util.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.data.redis")
data class CacheProperty(
    val host: String,
    val port: Int,
)