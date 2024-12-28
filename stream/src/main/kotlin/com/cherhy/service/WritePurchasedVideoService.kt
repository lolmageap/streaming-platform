package com.cherhy.service

import com.cherhy.common.util.model.Price
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.VideoId
import com.cherhy.repository.PurchasedVideoRepository

class WritePurchasedVideoService(
    private val purchasedVideoRepository: PurchasedVideoRepository,
) {
    suspend fun save(
        userId: UserId,
        videoId: VideoId,
        price: Price,
    ) {
        purchasedVideoRepository.save(
            userId,
            videoId,
            price,
        )
    }
}