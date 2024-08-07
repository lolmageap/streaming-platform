package com.cherhy.util

import com.cherhy.domain.VideoSize

object ContentRangeGenerator {
    fun generate(
        lastVideoByte: Byte?,
        currentVideoSize: VideoSize,
        videoTotalSize: VideoSize,
    ): String {
        val lastVideoSize = lastVideoByte?.toLong() ?: 0
        return "bytes=${lastVideoSize + currentVideoSize.value}-${videoTotalSize.value}"
    }
}