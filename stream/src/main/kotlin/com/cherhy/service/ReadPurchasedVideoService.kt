package com.cherhy.service

import com.cherhy.common.util.model.UserId
import com.cherhy.domain.VideoId
import com.cherhy.repository.PurchasedVideoRepository

class ReadPurchasedVideoService(
    private val purchasedVideoRepository: PurchasedVideoRepository,
) {
    suspend fun ifExists(
        userId: UserId,
        videoId: VideoId,
        block: () -> RuntimeException,
    ) {
        val isExists = purchasedVideoRepository.isExists(userId, videoId)
        if (isExists) throw block()
    }
}