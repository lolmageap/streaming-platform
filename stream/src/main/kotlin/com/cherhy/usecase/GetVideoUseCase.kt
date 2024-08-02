package com.cherhy.usecase

import com.cherhy.api.GetVideoResponse
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.*
import com.cherhy.plugins.MinioFactory
import com.cherhy.plugins.reactiveTransaction
import com.cherhy.service.ReadVideoService
import com.cherhy.util.ApplicationConfigUtils
import com.cherhy.util.extension.download
import com.cherhy.util.model.Bucket
import com.cherhy.util.property.MinioProperty
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