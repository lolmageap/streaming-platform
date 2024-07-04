package com.cherhy.common.config

import com.cherhy.common.annotation.UserIdFromHeader
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class UserIdFromHeaderAspect(
    private val userIdResolver: UserIdResolver,
) {
    @Before("@annotation(com.cherhy.common.annotation.UserIdFromHeader)")
    fun getUserId(joinPoint: JoinPoint) {
        val userId = userIdResolver.resolve()

        val methodSignature = joinPoint.signature as MethodSignature
        val annotations = methodSignature.method.parameterAnnotations

        for (i in annotations.indices) {
            for (annotation in annotations[i]) {
                if (annotation is UserIdFromHeader) {
                    joinPoint.args[i] = userId
                    break
                }
            }
        }
    }
}