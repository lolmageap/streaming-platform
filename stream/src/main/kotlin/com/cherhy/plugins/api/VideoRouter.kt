package com.cherhy.plugins.api

import com.cherhy.common.util.Stream
import com.cherhy.plugins.usecase.GetVideoUseCase
import com.cherhy.plugins.util.extension.lastWatchedCheckpoint
import com.cherhy.plugins.util.extension.pathVariable
import com.cherhy.plugins.util.extension.userId
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.video() {
    val getVideoUseCase: GetVideoUseCase by inject()
    get(Stream.Video.GET_VIDEO) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val videoId = call.pathVariable.videoId
        val lastVideoByte = call.request.lastWatchedCheckpoint

        getVideoUseCase.execute(userId, postId, videoId, lastVideoByte)
    }
}