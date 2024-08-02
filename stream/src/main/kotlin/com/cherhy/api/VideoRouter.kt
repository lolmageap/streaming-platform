package com.cherhy.api

import com.cherhy.common.util.Stream.Video.GET_VIDEO
import com.cherhy.usecase.GetVideoUseCase
import com.cherhy.util.ContentRangeGenerator
import com.cherhy.util.extension.lastWatchedCheckpoint
import com.cherhy.util.extension.pathVariable
import com.cherhy.util.extension.userId
import com.google.common.net.HttpHeaders.CONTENT_RANGE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.video() {
    val getVideoUseCase by inject<GetVideoUseCase>()

    get(GET_VIDEO) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val videoId = call.pathVariable.videoId
        val lastVideoByte = call.request.lastWatchedCheckpoint

        val video = getVideoUseCase.execute(userId, postId, videoId, lastVideoByte)

        val contentRangeValue =
            ContentRangeGenerator.generate(lastVideoByte, video.currentSize, video.totalSize)

        call.respondBytes(video.videoStream, ContentType.Video.MP4)
        call.respond(HttpStatusCode.OK)
        call.response.header(CONTENT_RANGE, contentRangeValue)
    }
}