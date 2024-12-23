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
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component
import java.time.Duration

@Aspect
@Component
class CacheWithLockAspect(
    private val lockableRedisCacheManager: LockableRedisCacheManager,
) {
    private val spelParser = SpelExpressionParser()

    @Around("@annotation(cacheWithLock)")
    @OptIn(ExperimentalCoroutinesApi::class)
    fun handleCacheWithLock(
        joinPoint: ProceedingJoinPoint,
        cacheWithLock: CacheWithLock,
    ): Any {
        val key = parseSpelExpression(cacheWithLock.key, joinPoint)

        return CoroutineScope(Default).async {
            lockableRedisCacheManager.getWithLock(
                cacheName = cacheWithLock.cacheNames.joinToString { it },
                key = key,
                lockKey = cacheWithLock.key,
                ttl = Duration.ofMillis(cacheWithLock.ttl),
            ) {
                joinPoint.proceed()
            }
        }.getCompleted()
    }

    private fun parseSpelExpression(expression: String, joinPoint: ProceedingJoinPoint): String {
        val method = (joinPoint.signature as MethodSignature).method
        val context = StandardEvaluationContext()

        val parameterNames = method.parameters.map { it.name }
        val args = joinPoint.args

        parameterNames.forEachIndexed { index, paramName ->
            context.setVariable(paramName, args[index])
        }

        return spelParser.parseExpression(expression).getValue(context, String::class.java)
            ?: throw IllegalArgumentException("SpEL 평가 실패: $expression")
    }
}