package com.cherhy.util

import com.cherhy.common.util.POST_ID
import com.cherhy.common.util.VIDEO_ID
import com.cherhy.domain.PostId
import com.cherhy.domain.VideoId
import extension.ktor.PathVariable

val PathVariable.postId
    get() = this[POST_ID].toLongOrNull()?.let(PostId::of)
        ?: throw IllegalArgumentException("$POST_ID is required.")

val PathVariable.videoId
    get() = this[VIDEO_ID].toLongOrNull()?.let(VideoId::of)
        ?: throw IllegalArgumentException("$VIDEO_ID is required.")