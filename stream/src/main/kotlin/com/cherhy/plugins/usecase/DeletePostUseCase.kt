package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.service.ReadPostService
import com.cherhy.plugins.service.ReadVideoService
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.util.ApplicationConfigUtils
import com.cherhy.plugins.util.property.MinioProperty.BUCKET
import io.minio.RemoveObjectArgs
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class DeletePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
    private val readPostService: ReadPostService,
    private val readVideoService: ReadVideoService,
) {
    private val minioClient = MinioFactory.newInstance()

    // TODO : Ktorm의 transaction 처리 해야함
    suspend fun execute(
        userId: UserId,
        postId: PostId,
    ) {
        readPostService.ifNotExist(
            userId,
            postId,
        )

        val originalVideo = readVideoService.get(postId)

        writePostService.delete(
            userId,
            postId,
        )

        writeVideoService.delete(
            userId,
            originalVideo.id,
        )

        withContext(IO) {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .`object`(originalVideo.uniqueName.value)
                    .build()
            )
        }
    }

    companion object {
        @JvmStatic
        private val bucket = ApplicationConfigUtils.getMinio(BUCKET)
    }
}