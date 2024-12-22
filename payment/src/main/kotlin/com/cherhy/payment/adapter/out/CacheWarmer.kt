package com.cherhy.payment.adapter.out

import com.cherhy.common.util.CacheConstant.TEST
import jakarta.annotation.PostConstruct
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class CacheWarmer(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    @PostConstruct
    fun warm() {
        stringRedisTemplate.opsForValue().get(TEST)
            ?: stringRedisTemplate.opsForValue().set(TEST, "test")
    }
}