package com.cherhy.payment.exception

data class RedisLockException(
    override val message: String = "Failed to acquire lock",
) : RuntimeException(message)