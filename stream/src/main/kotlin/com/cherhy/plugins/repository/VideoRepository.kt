package com.cherhy.plugins.repository

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.VideoDetailResponse
import com.cherhy.plugins.domain.*

interface VideoRepository {
    suspend fun save(
        userId: UserId,
        postId: PostId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension,
    ): VideoId

    suspend fun update(
        videoId: VideoId,
        userId: UserId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension
    )

    suspend fun delete(
        userId: UserId,
        videoId: VideoId,
    )

    suspend fun find(
        videoId: VideoId,
    ): VideoDetailResponse

    suspend fun find(
        postId: PostId,
    ): VideoDetailResponse
}

class VideoRepositoryImpl : VideoRepository {
    override suspend fun save(
        userId: UserId,
        postId: PostId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension,
    ): VideoId {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        videoId: VideoId,
        userId: UserId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(
        userId: UserId,
        videoId: VideoId,
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun find(
        videoId: VideoId,
    ): VideoDetailResponse {
        TODO("Not yet implemented")
    }

    override suspend fun find(
        postId: PostId,
    ): VideoDetailResponse {
        TODO("Not yet implemented")
    }
}