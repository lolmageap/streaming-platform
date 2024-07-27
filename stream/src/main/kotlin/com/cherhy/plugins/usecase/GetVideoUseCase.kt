package com.cherhy.plugins.usecase

import com.cherhy.common.util.model.UserId
import com.cherhy.plugins.api.GetVideoResponse
import com.cherhy.plugins.config.MinioFactory
import com.cherhy.plugins.config.reactiveTransaction
import com.cherhy.plugins.domain.*
import com.cherhy.plugins.service.ReadVideoService
import com.cherhy.plugins.util.ApplicationConfigUtils
import com.cherhy.plugins.util.extension.download
import com.cherhy.plugins.util.model.Bucket
import com.cherhy.plugins.util.property.MinioProperty
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.time.ZonedDateTime

class GetVideoUseCase(
    private val readVideoService: ReadVideoService,
    private val coroutineDatabase: CoroutineDatabase,
) {
    private val minioFactory = MinioFactory.newInstance()

    suspend fun execute(
        userId: UserId,
        postId: PostId,
        videoId: VideoId,
        lastVideoByte: Byte?,
    ) =
        reactiveTransaction {
            val video = readVideoService.get(userId, postId, videoId)

            val videoStream =
                minioFactory.download(bucket, video.uniqueName, video.extension, lastVideoByte)
                    .readAllBytes()!!

            val actionHistory = ActionHistory.of(userId, Action.SHOW_VIDEO, ZonedDateTime.now())

            coroutineDatabase.actionHistory.insertOne(actionHistory)
            GetVideoResponse.of(videoStream, video.size)
        }

    companion object {
        @JvmStatic
        private val bucket = Bucket.of(
            ApplicationConfigUtils.getMinio(MinioProperty.BUCKET)
        )
    }
}