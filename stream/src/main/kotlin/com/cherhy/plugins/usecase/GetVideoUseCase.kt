package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.config.reactiveTransaction
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.domain.VideoId
import com.cherhy.plugins.service.ReadVideoService

class GetVideoUseCase(
    private val readVideoService: ReadVideoService,
) {
    private val minioFactory = MinioFactory.newInstance()

    suspend fun execute(
        userId: UserId,
        postId: PostId,
        videoId: VideoId,
        lastVideoByte: Byte?,
    ): Nothing =
        reactiveTransaction {
            TODO()
        }
}