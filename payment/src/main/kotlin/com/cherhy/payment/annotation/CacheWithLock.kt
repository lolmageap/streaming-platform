package com.cherhy.payment.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class CacheWithLock(
    val cacheName: String,
    val key: String = "",
    val ttl: Long = 300_000,
)