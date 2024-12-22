package com.cherhy.payment.adapter.out

import org.springframework.cache.CacheManager
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class LockableRedisCacheManager(
    private val cacheManager: CacheManager,
    private val distributedLock: DistributedLock
) {
    @Suppress("UNCHECKED_CAST")
    suspend fun <T> getWithLock(
        cacheName: String,
        key: String,
        lockKey: String,
        ttl: Duration,
        block: () -> T,
    ): T {
        val cache = cacheManager.getCache(cacheName)
            ?: throw IllegalArgumentException("Cache $cacheName not found")

        val cachedValue = cache.get(key, Any::class.java)
        if (cachedValue != null) return cachedValue as T

        return distributedLock.redLock(lockKey) {
            val doubleCheckedValue = cache.get(key, Any::class.java)
            if (doubleCheckedValue != null) return@redLock doubleCheckedValue as T

            val value = block()
            cache.put(key, value)
            value
        }
    }
}