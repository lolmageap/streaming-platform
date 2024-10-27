package com.cherhy.payment.adapter.out

import com.cherhy.payment.util.lib.get
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class CacheReader(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun read(
        key: String
    ) =
        runCatching {
            val value = stringRedisTemplate.get(key)
            Result.success(value)
        }.getOrElse {
            Result.failure(it)
        }
}