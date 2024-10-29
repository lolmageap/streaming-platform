package com.cherhy.util.extension

import com.cherhy.api.VideoRequest
import com.cherhy.domain.VideoName
import com.cherhy.domain.VideoSize
import com.cherhy.domain.VideoUniqueName
import com.cherhy.util.VideoValidator
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

suspend fun ApplicationCall.getVideo(): VideoRequest {
    val multipart = this.receiveMultipart()

    val videoName = multipart.readPart()
        ?.name
        ?: throw IllegalArgumentException("video is required.")

    val byteArrayOutputStream = ByteArrayOutputStream()
    multipart.forEachPart { part ->
        if (part is PartData.FileItem) {
            part.streamProvider().use { inputStream ->
                inputStream.copyTo(byteArrayOutputStream)
            }
        }
        part.dispose()
    }

    val data = ByteArrayInputStream(
        byteArrayOutputStream.toByteArray()
    )

    val videoSize = data.available().toLong()
    VideoValidator.validate(videoName, videoSize)

    return VideoRequest.of(
        name = VideoName.of(videoName),
        uniqueName = VideoUniqueName.of(UUID.randomUUID().toString()),
        data = data,
        size = VideoSize.of(videoSize),
    )
}