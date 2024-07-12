package com.cherhy.plugins.config

import com.cherhy.plugins.util.ApplicationConfigUtils.getMinio
import com.cherhy.plugins.util.property.MinioProperty.ACCESS_KEY
import com.cherhy.plugins.util.property.MinioProperty.SECRET_KEY
import com.cherhy.plugins.util.property.MinioProperty.URL
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