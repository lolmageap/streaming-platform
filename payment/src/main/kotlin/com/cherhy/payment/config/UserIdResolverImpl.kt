package com.cherhy.payment.config

import com.cherhy.common.config.UserIdResolver
import org.springframework.http.server.reactive.ServerHttpRequest

class UserIdResolverImpl(
    private val httpRequest: ServerHttpRequest,
): UserIdResolver {
    override fun resolve() =
        httpRequest.headers.getFirst("user-id")?.toLong()
            ?: throw IllegalArgumentException("user id가 없습니다.")
}