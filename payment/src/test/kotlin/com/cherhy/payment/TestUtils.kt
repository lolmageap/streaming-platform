package com.cherhy.payment

import org.springframework.data.redis.core.StringRedisTemplate

internal fun StringRedisTemplate.deleteAll() {
    this.connectionFactory!!.connection.serverCommands().flushAll()
}