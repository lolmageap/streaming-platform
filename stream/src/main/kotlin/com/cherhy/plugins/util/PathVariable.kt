package com.cherhy.plugins.util

import com.cherhy.common.util.POST_ID
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.domain.VideoId
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

    val videoId = call.parameters["videoId"]
        ?.toLongOrNull()?.let(VideoId::of)
        ?: throw IllegalArgumentException("videoId is required.")

    private val path = call.parameters
}