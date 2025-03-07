package com.cherhy.util

import com.cherhy.common.util.POST_ID
import com.cherhy.common.util.VIDEO_ID
import com.cherhy.domain.PostId
import com.cherhy.domain.VideoId
import io.ktor.server.application.*

class PathParameter(
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

    val videoId = call.parameters[VIDEO_ID]
        ?.toLongOrNull()?.let(VideoId::of)
        ?: throw IllegalArgumentException("$VIDEO_ID is required.")

    private val path = call.parameters
}