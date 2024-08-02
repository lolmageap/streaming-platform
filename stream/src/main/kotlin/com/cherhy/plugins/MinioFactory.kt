package com.cherhy.plugins

import com.cherhy.util.ApplicationConfigUtils.getMinio
import com.cherhy.util.property.MinioProperty.ACCESS_KEY
import com.cherhy.util.property.MinioProperty.SECRET_KEY
import com.cherhy.util.property.MinioProperty.URL
import io.minio.MinioClient

object MinioFactory {
    private val minioUrl = getMinio(URL)
    private val minioAccessKey = getMinio(ACCESS_KEY)
    private val minioSecretKey = getMinio(SECRET_KEY)

    fun newInstance() =
        MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(minioAccessKey, minioSecretKey)
            .build()!!
}