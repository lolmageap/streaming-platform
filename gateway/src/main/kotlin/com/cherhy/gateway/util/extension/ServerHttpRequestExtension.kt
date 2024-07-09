package com.cherhy.gateway.util.extension

import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.UserId
import com.cherhy.common.util.model.toUserId
import org.springframework.http.server.reactive.ServerHttpRequest

val ServerHttpRequest.accessToken: String?
    get() = this.headers.getFirst("Authorization")?.substringAfter("Bearer ")

var ServerHttpRequest.userId: UserId
    get() = this.headers.getFirst(USER_ID)?.toLong()?.toUserId()
        ?: throw IllegalArgumentException("${USER_ID}가 없습니다.")
    set(value) {
        this.mutate().header(USER_ID, value.toString()).build()
    }