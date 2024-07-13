package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.UpdatePostRequest
import com.cherhy.plugins.api.VideoRequest
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.domain.PostId
import com.cherhy.plugins.service.ReadPostService
import com.cherhy.plugins.service.ReadVideoService
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.util.ApplicationConfigUtils
import com.cherhy.plugins.util.property.MinioProperty.BUCKET
import com.cherhy.plugins.util.property.StreamingProperty.OBJECT_PART_SIZE
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class UpdatePostUseCase(
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
        updateVideo: VideoRequest,
        updatePost: UpdatePostRequest,
    ) {
        readPostService.ifNotExist(
            userId,
            postId,
        )

        val originalVideo = readVideoService.get(postId)

        writePostService.update(
            userId,
            postId,
            updatePost.title,
            updatePost.content,
            updatePost.category,
        )

        writeVideoService.update(
            originalVideo.id,
            userId,
            updateVideo.name,
            updateVideo.uniqueName,
            updateVideo.size,
            updateVideo.extension,
        )

        withContext(IO) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .`object`(updateVideo.uniqueName.value)
                    .stream(
                        updateVideo.data,
                        updateVideo.size.value,
                        OBJECT_PART_SIZE,
                    )
                    .build()
            )
        }

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