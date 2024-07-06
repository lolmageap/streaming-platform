package com.cherhy.plugins.util.extension

import com.cherhy.plugins.util.PathVariable
import com.cherhy.plugins.util.model.toUserId
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*

val mapper = jacksonObjectMapper()

val ApplicationCall.pathVariable
    get() = PathVariable(this)

val ApplicationCall.userId
    get() = this.request.headers["user-id"]?.toLongOrNull()?.toUserId()
        ?: throw IllegalArgumentException("user-id header is required")

inline fun <reified T : Any> ApplicationCall.getQueryParams(): T {
    return this.request.queryParameters.toClass()
}

inline fun <reified T : Any> Parameters.toClass(): T {
    val map = this.entries().associate {
        it.key to (it.value.getOrNull(0)
            ?: throw IllegalArgumentException("Missing value for key ${it.key}"))
    }
    return mapper.convertValue(map, T::class.java)
}