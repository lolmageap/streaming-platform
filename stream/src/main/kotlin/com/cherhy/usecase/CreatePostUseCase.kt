package com.cherhy.usecase

import com.cherhy.api.CreatePostRequest
import com.cherhy.api.VideoRequest
import com.cherhy.common.util.model.UserId
import com.cherhy.external.VideoStorage
import com.cherhy.plugins.reactiveTransaction
import com.cherhy.service.WritePostService
import com.cherhy.service.WriteVideoService
import com.cherhy.util.ApplicationConfigUtils
import com.cherhy.util.model.Bucket
import com.cherhy.util.property.MinioProperty.BUCKET

class CreatePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
    private val videoStorage: VideoStorage,
) {
    suspend fun execute(
        userId: UserId,
        video: VideoRequest,
        post: CreatePostRequest,
    ) =
        reactiveTransaction {
            val postId = writePostService.create(userId, post.title, post.content, post.category)

            writeVideoService.create(userId, postId, video.name, video.uniqueName, video.size, video.extension)
            videoStorage.upload(bucket, video.uniqueName, video.data, video.size, video.extension)
            Unit
        }

    companion object {
        @JvmStatic
        private val bucket = Bucket.of(
            ApplicationConfigUtils.getMinio(BUCKET)
        )
    }
}