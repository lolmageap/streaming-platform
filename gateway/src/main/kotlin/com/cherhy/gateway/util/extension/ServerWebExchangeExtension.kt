package com.cherhy.gateway.util.extension

import org.springframework.web.server.ServerWebExchange

val ServerWebExchange.accessToken
    get() = this.request.headers.getFirst("Authorization")
        ?.substringAfter("Bearer ")
        ?: throw IllegalArgumentException("토큰에 문제가 있습니다.")