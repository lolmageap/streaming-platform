package com.cherhy.payment.util.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.cache.redis")
data class CacheProperty(
    val timeToLive: Long,
    val keyPrefix: String,
)

@ConfigurationProperties(prefix = "spring.data.redis")
data class RedisProperty(
    val host: String,
    val port: Int,
)