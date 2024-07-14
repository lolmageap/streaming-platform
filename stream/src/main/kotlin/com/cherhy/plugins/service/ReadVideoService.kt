package com.cherhy.plugins.service

import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.domain.VideoId
import com.cherhy.plugins.repository.VideoRepository

class ReadVideoService(
    private val videoRepository: VideoRepository,
) {
    suspend fun get(
        videoId: VideoId,
    ) =
        videoRepository.find(
            videoId,
        ) ?: throw NoSuchElementException("Video not found")

    suspend fun get(
        postId: PostId,
    ) =
        videoRepository.find(
            postId,
        ) ?: throw NoSuchElementException("Video not found")
}