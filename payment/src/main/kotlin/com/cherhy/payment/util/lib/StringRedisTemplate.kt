package com.cherhy.payment.util.lib

import org.springframework.data.redis.core.StringRedisTemplate

fun StringRedisTemplate.get(
    key: String,
) = opsForValue().get(key)!!

fun StringRedisTemplate.set(
    key: String,
    value: String,
) = opsForValue().set(key, value)