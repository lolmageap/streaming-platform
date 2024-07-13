package com.cherhy.plugins.api

import com.cherhy.plugins.domain.VideoExtension
import com.cherhy.plugins.domain.VideoName
import com.cherhy.plugins.domain.VideoSize
import com.cherhy.plugins.domain.VideoUniqueName
import java.io.ByteArrayInputStream

data class VideoRequest private constructor(
    val name: VideoName,
    val uniqueName: VideoUniqueName,
    val data: ByteArrayInputStream,
    val size: VideoSize,
    val extension: VideoExtension,
) {
    companion object {
        fun of(
            name: VideoName,
            uniqueName: VideoUniqueName,
            data: ByteArrayInputStream,
            size: VideoSize,
        ) = VideoRequest(
            name = name,
            uniqueName = uniqueName,
            data = data,
            size = size,
            extension = VideoExtension.of("mp4"),
        )
    }
}