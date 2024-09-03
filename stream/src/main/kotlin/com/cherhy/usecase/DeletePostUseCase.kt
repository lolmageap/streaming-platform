package com.cherhy.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.domain.PostId
import com.cherhy.plugins.MinioFactory
import com.cherhy.plugins.reactiveTransaction
import com.cherhy.service.ReadPostService
import com.cherhy.service.ReadVideoService
import com.cherhy.service.WritePostService
import com.cherhy.service.WriteVideoService
import com.cherhy.util.ApplicationConfigUtils
import com.cherhy.util.extension.remove
import com.cherhy.util.model.Bucket
import com.cherhy.util.property.MinioProperty.BUCKET

class DeletePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
    private val readPostService: ReadPostService,
    private val readVideoService: ReadVideoService,
) {
    private val minioClient = MinioFactory.newInstance()

    suspend fun execute(
        userId: UserId,
        postId: PostId,
    ) =
        reactiveTransaction {
            readPostService.checkIfNotExists(userId, postId)

            val originalVideo = readVideoService.get(postId)

            writePostService.delete(userId, postId)

            writeVideoService.delete(userId, originalVideo.id)

            minioClient.remove(bucket, originalVideo.uniqueName, originalVideo.extension)
        }

    companion object {
        @JvmStatic
        private val bucket = Bucket.of(
            ApplicationConfigUtils.getMinio(BUCKET)
        )
    }
}