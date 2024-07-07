package com.cherhy.plugins.util.extension

import com.cherhy.plugins.api.CreateVideoRequest
import com.cherhy.plugins.util.PathVariable
import com.cherhy.plugins.util.VideoValidator
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

val mapper = jacksonObjectMapper()

val ApplicationCall.pathVariable
    get() = PathVariable(this)

inline fun <reified T : Any> ApplicationCall.getQueryParams(): T {
    return this.request.queryParameters.toClass()
}

suspend fun ApplicationCall.getVideo(): CreateVideoRequest {
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

    return CreateVideoRequest.of(
        name = videoName,
        uniqueName = UUID.randomUUID().toString(),
        data = data,
        size = videoSize,
    )
}

inline fun <reified T : Any> Parameters.toClass(): T {
    val map = this.entries().associate {
        it.key to (it.value.getOrNull(0)
            ?: throw IllegalArgumentException("Missing value for key ${it.key}"))
    }
    return mapper.convertValue(map, T::class.java)
}