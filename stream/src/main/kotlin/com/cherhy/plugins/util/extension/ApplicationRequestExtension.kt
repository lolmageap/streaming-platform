package com.cherhy.plugins.util.extension

import com.cherhy.common.util.model.toUserId
import com.google.common.net.HttpHeaders.CONTENT_RANGE
import io.ktor.server.request.*

val ApplicationRequest.userId
    get() = this.headers["user-id"]?.toLongOrNull()?.toUserId()
        ?: throw IllegalArgumentException("user-id header is required")

val ApplicationRequest.lastWatchedCheckpoint
    get() = this.headers[CONTENT_RANGE]
        ?.substringAfter("bytes=")
        ?.substringBefore("-")
        ?.toByte()