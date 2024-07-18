package com.cherhy.plugins.util.extension

import com.cherhy.plugins.domain.VideoExtension
import com.cherhy.plugins.domain.VideoSize
import com.cherhy.plugins.domain.VideoUniqueName
import com.cherhy.plugins.util.model.Bucket
import com.cherhy.plugins.util.property.StreamingProperty
import io.minio.MinioClient
import io.minio.PutObjectArgs
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
            io.minio.RemoveObjectArgs.builder()
                .bucket(bucket.value)
                .`object`("${uniqueName.value}.${extension.value}")
                .build()
        )
    }