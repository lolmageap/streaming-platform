package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.CreatePostRequest
import com.cherhy.plugins.api.VideoRequest
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.service.WritePostService
import com.cherhy.plugins.service.WriteVideoService
import com.cherhy.plugins.util.ApplicationConfigUtils
import com.cherhy.plugins.util.property.MinioProperty.BUCKET
import com.cherhy.plugins.util.property.StreamingProperty.OBJECT_PART_SIZE
import io.minio.PutObjectArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreatePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
) {
    private val minioClient = MinioFactory.newInstance()

    // TODO : Ktorm의 transaction 처리 해야함
    suspend fun execute(
        userId: UserId,
        video: VideoRequest,
        post: CreatePostRequest,
    ) {
        val postId = writePostService.create(
            userId,
            post.title,
            post.content,
            post.category,
        )

        writeVideoService.create(
            userId,
            postId,
            video.name,
            video.uniqueName,
            video.size,
            video.extension,
        )

        withContext(Dispatchers.IO) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .`object`(video.uniqueName.value)
                    .stream(
                        video.data,
                        video.size.value,
                        OBJECT_PART_SIZE,
                    )
                    .build()
            )
        }
    }

    companion object {
        @JvmStatic
        private val bucket = ApplicationConfigUtils.getMinio(BUCKET)
    }
}