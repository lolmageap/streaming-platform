package com.cherhy.api

import com.cherhy.domain.VideoExtension
import com.cherhy.domain.VideoName
import com.cherhy.domain.VideoSize
import com.cherhy.domain.VideoUniqueName
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