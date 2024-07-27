package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.UpdatePostRequest
import com.cherhy.plugins.api.VideoRequest
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.config.reactiveTransaction
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.service.ReadPostService
import com.cherhy.plugins.service.ReadVideoService
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.util.ApplicationConfigUtils
import com.cherhy.plugins.util.extension.remove
import com.cherhy.plugins.util.extension.upload
import com.cherhy.plugins.util.model.Bucket
import com.cherhy.plugins.util.property.MinioProperty.BUCKET

class UpdatePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
    private val readPostService: ReadPostService,
    private val readVideoService: ReadVideoService,
) {
    private val minioClient = MinioFactory.newInstance()

    suspend fun execute(
        userId: UserId,
        postId: PostId,
        updateVideo: VideoRequest,
        updatePost: UpdatePostRequest,
    ) =
        reactiveTransaction {
            readPostService.ifNotExist(userId, postId)

            val originalVideo = readVideoService.get(postId)

            writePostService.update(userId, postId, updatePost.title, updatePost.content, updatePost.category)

            writeVideoService.update(
                originalVideo.id,
                userId,
                updateVideo.name,
                updateVideo.uniqueName,
                updateVideo.size,
                updateVideo.extension,
            )

            minioClient.upload(
                bucket,
                updateVideo.uniqueName,
                updateVideo.data,
                updateVideo.size,
                updateVideo.extension,
            )

            minioClient.remove(bucket, originalVideo.uniqueName, originalVideo.extension)
        }

    companion object {
        @JvmStatic
        private val bucket = Bucket.of(
            ApplicationConfigUtils.getMinio(BUCKET)
        )
    }
}