package com.cherhy.payment.annotation

import org.springframework.aot.hint.annotation.Reflective
import org.springframework.core.annotation.AliasFor
import java.lang.annotation.Inherited
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*

@Target(
    CLASS,
    FUNCTION,
    PROPERTY_GETTER,
    PROPERTY_SETTER,
)
@Retention(RUNTIME)
@Inherited
@MustBeDocumented
@Reflective
annotation class CacheWithLock(
    @get:AliasFor("cacheNames")
    vararg val value: String = [],

    @get:AliasFor("value")
    val cacheNames: Array<String> = [],

    val key: String = "",

    val ttl: Long = 300_000,
)