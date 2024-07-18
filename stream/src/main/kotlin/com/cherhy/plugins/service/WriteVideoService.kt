package com.cherhy.plugins.service

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.domain.*
import com.cherhy.plugins.repository.VideoRepository

class WriteVideoService(
    private val videoRepository: VideoRepository,
) {
    suspend fun create(
        userId: UserId,
        postId: PostId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension,
    ) =
        videoRepository.save(
            userId,
            postId,
            name,
            uniqueName,
            size,
            extension,
        )

    suspend fun update(
        videoId: VideoId,
        userId: UserId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension,
    ) =
        videoRepository.update(
            videoId,
            userId,
            name,
            uniqueName,
            size,
            extension,
        )

    suspend fun delete(
        userId: UserId,
        videoId: VideoId,
    ) =
        videoRepository.delete(
            userId,
            videoId,
        )
}