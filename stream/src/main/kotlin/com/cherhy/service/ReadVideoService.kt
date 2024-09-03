package com.cherhy.service

import com.cherhy.common.util.model.UserId
import com.cherhy.domain.PostId
import com.cherhy.domain.VideoId
import com.cherhy.repository.VideoRepository

class ReadVideoService(
    private val videoRepository: VideoRepository,
) {
    suspend fun get(
        videoId: VideoId,
    ) =
        videoRepository.findOne(
            videoId,
        ) ?: throw NoSuchElementException("Video not found")

    suspend fun get(
        postId: PostId,
    ) =
        videoRepository.findOne(
            postId,
        ) ?: throw NoSuchElementException("Video not found")

    suspend fun get(
        userId: UserId,
        postId: PostId,
        videoId: VideoId,
    ) =
        videoRepository.findOne(
            userId,
            postId,
            videoId,
        ) ?: throw NoSuchElementException("Video not found")
}