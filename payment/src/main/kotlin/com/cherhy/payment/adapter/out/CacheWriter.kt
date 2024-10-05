package com.cherhy.payment.adapter.out

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class CacheWriter(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun write(
        key: String,
        value: String,
    ) {
        stringRedisTemplate.opsForValue().set(key, value)
    }
}