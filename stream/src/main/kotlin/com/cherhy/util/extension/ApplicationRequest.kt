package com.cherhy.util.extension

import com.cherhy.common.util.USER_ID
import com.cherhy.common.util.model.toUserId
import com.google.common.net.HttpHeaders.CONTENT_RANGE
import io.ktor.server.request.*

val ApplicationRequest.userId
    get() = this.headers[USER_ID]?.toLongOrNull()?.toUserId()
        ?: throw IllegalArgumentException("$USER_ID header is required")

val ApplicationRequest.lastWatchedCheckpoint
    get() = this.headers[CONTENT_RANGE]
        ?.substringAfter("bytes=")
        ?.substringBefore("-")
        ?.toByte()