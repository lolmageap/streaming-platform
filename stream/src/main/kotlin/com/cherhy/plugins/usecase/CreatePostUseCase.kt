package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.CreatePostRequest
import com.cherhy.plugins.api.VideoRequest
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.config.reactiveTransaction
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.util.ApplicationConfigUtils
import com.cherhy.plugins.util.extension.upload
import com.cherhy.plugins.util.model.Bucket
import com.cherhy.plugins.util.property.MinioProperty.BUCKET

class CreatePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
) {
    private val minioClient = MinioFactory.newInstance()

    suspend fun execute(
        userId: UserId,
        video: VideoRequest,
        post: CreatePostRequest,
    ) =
        reactiveTransaction {
            val postId = writePostService.create(userId, post.title, post.content, post.category)

            writeVideoService.create(userId, postId, video.name, video.uniqueName, video.size, video.extension)
            minioClient.upload(bucket, video.uniqueName, video.data, video.size, video.extension)
            Unit
        }

    companion object {
        @JvmStatic
        private val bucket = Bucket.of(
            ApplicationConfigUtils.getMinio(BUCKET)
        )
    }
}