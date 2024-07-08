package com.cherhy.plugins.service

import com.cherhy.plugins.repository.VideoRepository

class ReadVideoService(
    private val videoRepository: VideoRepository,
) {
    suspend fun get() {
        TODO()
    }
}