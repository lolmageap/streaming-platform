package com.cherhy.plugins.service

import com.cherhy.plugins.repository.VideoRepository

class WriteVideoService(
    private val videoRepository: VideoRepository,
) {
    suspend fun delete() {
        TODO()
    }
}