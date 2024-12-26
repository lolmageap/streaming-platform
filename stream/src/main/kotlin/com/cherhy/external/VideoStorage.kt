package com.cherhy.external

import com.cherhy.domain.VideoExtension
import com.cherhy.domain.VideoSize
import com.cherhy.domain.VideoUniqueName
import com.cherhy.plugins.MinioFactory
import com.cherhy.util.model.Bucket
import com.cherhy.util.property.StreamingProperty
import io.minio.GetObjectArgs
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream

class VideoStorage {
    private val minioClient = MinioFactory.newInstance()

    suspend fun upload(
        bucket: Bucket,
        uniqueName: VideoUniqueName,
        data: ByteArrayInputStream,
        size: VideoSize,
        extension: VideoExtension,
    ) =
        withContext(Dispatchers.IO) {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket.value)
                    .`object`("${uniqueName.value}.${extension.value}")
                    .stream(
                        data,
                        size.value,
                        StreamingProperty.OBJECT_PART_SIZE,
                    )
                    .build()
            )
        }!!

    suspend fun remove(
        bucket: Bucket,
        uniqueName: VideoUniqueName,
        extension: VideoExtension,
    ) =
        withContext(Dispatchers.IO) {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucket.value)
                    .`object`("${uniqueName.value}.${extension.value}")
                    .build()
            )
        }

    suspend fun download(
        bucket: Bucket,
        uniqueName: VideoUniqueName,
        extension: VideoExtension,
        lastByte: Byte?,
    ) =
        withContext(Dispatchers.IO) {
            minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(bucket.value)
                    .`object`("${uniqueName.value}.${extension.value}")
                    .length(StreamingProperty.CHUNK_SIZE)
                    .offset(lastByte?.toLong() ?: 0)
                    .build()
            ) ?: throw NoSuchElementException("Video not found")
        }
}