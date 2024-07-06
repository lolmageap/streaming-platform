package com.cherhy.plugins.util

import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.util.constant.POST_ID
import io.ktor.server.application.*

class PathVariable(
    call: ApplicationCall,
) {
    operator fun get(
        key: String,
    ) =
        path[key]
            ?: throw IllegalArgumentException("$key is required.")

    val postId = call.parameters[POST_ID]
        ?.toLongOrNull()?.let(PostId::of)
        ?: throw IllegalArgumentException("$POST_ID is required.")

    private val path = call.parameters
}