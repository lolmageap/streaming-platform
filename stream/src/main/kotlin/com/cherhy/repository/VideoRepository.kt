package com.cherhy.repository

import com.cherhy.api.VideoDetailResponse
import com.cherhy.common.util.extension.noReturn
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.*
import com.cherhy.plugins.database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.find

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
        extension: VideoExtension,
    )

    suspend fun delete(
        userId: UserId,
        videoId: VideoId,
    )

    suspend fun findOne(
        videoId: VideoId,
    ): VideoDetailResponse?

    suspend fun findOne(
        postId: PostId,
    ): VideoDetailResponse?

    suspend fun findOne(
        userId: UserId,
        postId: PostId,
        videoId: VideoId,
    ): VideoDetailResponse?

    suspend fun isExists(
        videoId: VideoId,
    ): Boolean
}

class VideoRepositoryImpl : VideoRepository {
    override suspend fun save(
        userId: UserId,
        postId: PostId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension,
    ) =
        database.insert(Videos) {
            set(it.owner, userId.value)
            set(it.post, postId.value)
            set(it.name, name.value)
            set(it.uniqueName, uniqueName.value)
            set(it.size, size.value)
            set(it.extension, extension.value)
        }.toVideoId()

    override suspend fun update(
        videoId: VideoId,
        userId: UserId,
        name: VideoName,
        uniqueName: VideoUniqueName,
        size: VideoSize,
        extension: VideoExtension,
    ) =
        database.update(Videos) {
            set(it.name, name.value)
            set(it.uniqueName, uniqueName.value)
            set(it.size, size.value)
            set(it.extension, extension.value)
            where {
                it.id eq videoId.value
                it.owner eq userId.value
            }
        }.noReturn

    override suspend fun delete(
        userId: UserId,
        videoId: VideoId,
    ) =
        database.delete(Videos) {
            it.id eq videoId.value
            it.owner eq userId.value
        }.noReturn

    override suspend fun findOne(
        videoId: VideoId,
    ) =
        database.videos.find {
            it.id eq videoId.value
        }?.let(VideoDetailResponse::of)

    override suspend fun findOne(
        postId: PostId,
    ) =
        database.videos.find {
            it.post eq postId.value
        }?.let(VideoDetailResponse::of)

    override suspend fun findOne(
        userId: UserId,
        postId: PostId,
        videoId: VideoId,
    ) =
        database.videos.find {
            it.owner eq userId.value
            it.post eq postId.value
            it.id eq videoId.value
        }?.let(VideoDetailResponse::of)

    override suspend fun isExists(
        videoId: VideoId,
    ) =
        database.videos.find { it.id eq videoId.value } != null
}