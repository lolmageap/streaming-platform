package com.cherhy.plugins.api

import com.cherhy.common.util.Stream
import com.cherhy.plugins.util.extension.lastWatchedCheckpoint
import com.cherhy.plugins.util.extension.pathVariable
import com.cherhy.plugins.util.extension.userId
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.video() {
    get(Stream.Video.GET_VIDEO) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val videoId = call.pathVariable.videoId
        val lastVideoByte = call.request.lastWatchedCheckpoint

        // TODO: Get video by postId, videoId, userId and lastVideoByte
    }
}