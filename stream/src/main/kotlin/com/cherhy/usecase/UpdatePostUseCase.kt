package com.cherhy.usecase

import com.cherhy.api.VideoRequest
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.PostCategory
import com.cherhy.domain.PostContent
import com.cherhy.domain.PostId
import com.cherhy.domain.PostTitle
import com.cherhy.external.VideoStorage
import com.cherhy.plugins.reactiveTransaction
import com.cherhy.service.ReadPostService
import com.cherhy.service.ReadVideoService
import com.cherhy.service.WritePostService
import com.cherhy.service.WriteVideoService
import com.cherhy.util.ApplicationConfigUtils
import com.cherhy.util.model.Bucket
import com.cherhy.util.property.MinioProperty.BUCKET

class UpdatePostUseCase(
    private val writePostService: WritePostService,
    private val writeVideoService: WriteVideoService,
    private val readPostService: ReadPostService,
    private val readVideoService: ReadVideoService,
    private val videoStorage: VideoStorage,
) {
    suspend fun execute(
        userId: UserId,
        postId: PostId,
        updateVideo: VideoRequest,
        updatePost: UpdatePostCommand,
    ) =
        reactiveTransaction {
            readPostService.checkIfNotExists(userId, postId)

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

            videoStorage.upload(
                bucket,
                updateVideo.uniqueName,
                updateVideo.data,
                updateVideo.size,
                updateVideo.extension,
            )

            videoStorage.remove(bucket, originalVideo.uniqueName, originalVideo.extension)
        }

    companion object {
        @JvmStatic
        private val bucket = Bucket.of(
            ApplicationConfigUtils.getMinio(BUCKET)
        )
    }
}

data class UpdatePostCommand(
    val title: PostTitle,
    val content: PostContent,
    val category: PostCategory,
)