package com.cherhy.util.extension

import com.cherhy.domain.VideoExtension
import com.cherhy.domain.VideoSize
import com.cherhy.domain.VideoUniqueName
import com.cherhy.util.model.Bucket
import com.cherhy.util.property.StreamingProperty
import com.cherhy.util.property.StreamingProperty.CHUNK_SIZE
import io.minio.GetObjectArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream

suspend fun MinioClient.upload(
    bucket: Bucket,
    uniqueName: VideoUniqueName,
    data: ByteArrayInputStream,
    size: VideoSize,
    extension: VideoExtension,
) =
    withContext(Dispatchers.IO) {
        putObject(
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

suspend fun MinioClient.remove(
    bucket: Bucket,
    uniqueName: VideoUniqueName,
    extension: VideoExtension,
) =
    withContext(Dispatchers.IO) {
        removeObject(
            RemoveObjectArgs.builder()
                .bucket(bucket.value)
                .`object`("${uniqueName.value}.${extension.value}")
                .build()
        )
    }

suspend fun MinioClient.download(
    bucket: Bucket,
    uniqueName: VideoUniqueName,
    extension: VideoExtension,
    lastByte: Byte?,
) =
    withContext(Dispatchers.IO) {
        getObject(
            GetObjectArgs.builder()
                .bucket(bucket.value)
                .`object`("${uniqueName.value}.${extension.value}")
                .length(CHUNK_SIZE)
                .offset(lastByte?.toLong() ?: 0)
                .build()
        ) ?: throw NoSuchElementException("Video not found")
    }