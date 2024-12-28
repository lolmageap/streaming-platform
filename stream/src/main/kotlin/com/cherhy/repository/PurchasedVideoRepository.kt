package com.cherhy.repository

import com.cherhy.common.util.model.Price
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.PurchasedVideos
import com.cherhy.domain.VideoId
import com.cherhy.domain.purchasedVideos
import com.cherhy.plugins.database
import org.ktorm.dsl.and
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.entity.find

interface PurchasedVideoRepository {
    suspend fun isExists(
        userId: UserId,
        videoId: VideoId,
    ): Boolean

    suspend fun save(
        userId: UserId,
        videoId: VideoId,
        price: Price,
    )
}

class PurchasedVideoRepositoryImpl : PurchasedVideoRepository {
    override suspend fun isExists(
        userId: UserId,
        videoId: VideoId
    ) =
        database.purchasedVideos.find { (it.userId eq userId.value) and (it.videoId eq videoId.value) }
            ?.let { true }
            ?: false

    override suspend fun save(
        userId: UserId,
        videoId: VideoId,
        price: Price,
    ) {
        database.insert(PurchasedVideos) {
            set(it.userId, userId.value)
            set(it.videoId, videoId.value)
            set(it.price, price.value)
        }
    }
}