package com.cherhy.plugins.api

import java.io.ByteArrayInputStream

data class CreateVideoRequest private constructor(
    val name: String,
    val uniqueName: String,
    val data: ByteArrayInputStream,
    val size: Long,
    val extension: String = "mp4",
) {
    companion object {
        fun of(
            name: String,
            uniqueName: String,
            data: ByteArrayInputStream,
            size: Long,
        ) = CreateVideoRequest(
            name = name,
            uniqueName = uniqueName,
            data = data,
            size = size,
        )
    }
}