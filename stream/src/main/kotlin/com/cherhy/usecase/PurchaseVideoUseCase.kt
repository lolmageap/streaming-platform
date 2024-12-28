package com.cherhy.usecase

import com.cherhy.common.util.event.VideoPurchaseFailedEvent
import com.cherhy.common.util.model.Price
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.VideoId
import com.cherhy.plugins.KafkaPublisher
import com.cherhy.plugins.reactiveTransaction
import com.cherhy.service.ReadPurchasedVideoService
import com.cherhy.service.ReadVideoService
import com.cherhy.service.WritePurchasedVideoService

class PurchaseVideoUseCase(
    private val kafkaPublisher: KafkaPublisher,
    private val readVideoService: ReadVideoService,
    private val readPurchasedVideoService: ReadPurchasedVideoService,
    private val writePurchasedVideoService: WritePurchasedVideoService,
) {
    suspend fun execute(
        videoId: VideoId,
        userId: UserId,
        price: Price,
    ) =
        runCatching {
            reactiveTransaction {
                readVideoService.ifNull(videoId) {
                    throw NoSuchElementException("Video not found")
                }

                readPurchasedVideoService.ifExists(userId, videoId) {
                    throw IllegalStateException("Already purchased")
                }

                writePurchasedVideoService.save(userId, videoId, price)
            }
        }.onFailure {
            kafkaPublisher.publish(
                VideoPurchaseFailedEvent(
                    userId = userId.value,
                    videoId = videoId.value,
                    price = price.value,
                )
            )
        }.getOrThrow()
}