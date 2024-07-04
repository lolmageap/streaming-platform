package com.cherhy.payment.util.extension

import org.springframework.http.server.reactive.ServerHttpRequest

val ServerHttpRequest.accessToken: String?
    get() = this.headers.getFirst("Authorization")?.substringAfter("Bearer ")

var ServerHttpRequest.userId: Long
    get() = this.headers.getFirst("user-id")?.toLong()
        ?: throw IllegalArgumentException("user id가 없습니다.")
    set(value) {
        this.mutate().header("user-id", value.toString()).build()
    }