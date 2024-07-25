package com.cherhy.plugins.api

import com.cherhy.common.util.Stream.Video.GET_VIDEO
import com.cherhy.plugins.domain.Action
import com.cherhy.plugins.domain.ActionHistory
import com.cherhy.plugins.domain.actionHistory
import com.cherhy.plugins.usecase.GetVideoUseCase
import com.cherhy.plugins.util.ContentRangeGenerator
import com.cherhy.plugins.util.extension.lastWatchedCheckpoint
import com.cherhy.plugins.util.extension.pathVariable
import com.cherhy.plugins.util.extension.userId
import com.google.common.net.HttpHeaders.CONTENT_RANGE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.time.ZonedDateTime

fun Route.video() {
    val getVideoUseCase: GetVideoUseCase by inject()
    val coroutineDatabase: CoroutineDatabase by inject()

    get(GET_VIDEO) {
        val userId = call.request.userId
        val postId = call.pathVariable.postId
        val videoId = call.pathVariable.videoId
        val lastVideoByte = call.request.lastWatchedCheckpoint

        val video = getVideoUseCase.execute(
            userId,
            postId,
            videoId,
            lastVideoByte,
        )

        val contentRangeValue = ContentRangeGenerator.generate(
            lastVideoByte,
            video.currentSize,
            video.totalSize,
        )

        val actionHistory = ActionHistory.of(
            userId,
            Action.SHOW_VIDEO,
            ZonedDateTime.now(),
        )

        coroutineDatabase.actionHistory.insertOne(actionHistory)

        call.respondBytes(video.videoStream, ContentType.Video.MP4)
        call.respond(HttpStatusCode.OK)
        call.response.header(CONTENT_RANGE, contentRangeValue)
    }
}