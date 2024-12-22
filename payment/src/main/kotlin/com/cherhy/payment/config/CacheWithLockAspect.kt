package com.cherhy.payment.config

import com.cherhy.payment.adapter.out.LockableRedisCacheManager
import com.cherhy.payment.annotation.CacheWithLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component
import java.time.Duration

@Aspect
@Component
class CacheWithLockAspect(
    private val lockableRedisCacheManager: LockableRedisCacheManager,
) {
    private val parser = SpelExpressionParser()

    @Around("@annotation(cacheWithLock)")
    @OptIn(ExperimentalCoroutinesApi::class)
    fun handleCacheWithLock(
        joinPoint: ProceedingJoinPoint,
        cacheWithLock: CacheWithLock,
    ): Any {
        val key = parseKey(cacheWithLock.key, joinPoint, joinPoint.args)

        return CoroutineScope(Default).async {
            lockableRedisCacheManager.getWithLock(
                cacheName = cacheWithLock.cacheName,
                key = key,
                lockKey = cacheWithLock.key,
                ttl = Duration.ofMillis(cacheWithLock.ttl),
            ) {
                joinPoint.proceed()
            }
        }.getCompleted()
    }


    // TODO: 지금 이거 미완성이라 다시 작성해야함
    private fun parseKey(
        keyExpression: String,
        joinPoint: ProceedingJoinPoint,
        methodArgs: Array<Any>,
    ): String {
        if (keyExpression.isBlank()) return ""

        val context = StandardEvaluationContext().apply {
            for ((index, arg) in methodArgs.withIndex()) {
                setVariable("p$index", arg)
            }
        }

        return parser.parseExpression(keyExpression).getValue(context, String::class.java) ?: ""
    }
}