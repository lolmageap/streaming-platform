package com.cherhy.payment.config

import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.toUserId
import org.springframework.http.server.reactive.ServerHttpRequest

class UserIdResolverImpl(
    private val httpRequest: ServerHttpRequest,
): UserIdResolver {
    override fun resolve() =
        httpRequest.headers.getFirst(USER_ID)?.toLong()?.toUserId()
            ?: throw IllegalArgumentException("${USER_ID}가 없습니다.")
}