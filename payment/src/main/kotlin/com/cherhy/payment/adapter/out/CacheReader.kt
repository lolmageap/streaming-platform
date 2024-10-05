package com.cherhy.payment.adapter.out

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class CacheReader(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun read(
        key: String
    ): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }
}