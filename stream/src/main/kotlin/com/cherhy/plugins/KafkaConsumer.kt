package com.cherhy.plugins

import com.cherhy.common.util.KafkaConstant.Topic.PURCHASE_VIDEO_TOPIC
import com.cherhy.common.util.event.PurchaseVideoEvent
import com.cherhy.common.util.model.Price
import com.cherhy.common.util.model.UserId
import com.cherhy.domain.VideoId
import com.cherhy.usecase.PurchaseVideoUseCase
import com.cherhy.util.extension.poll
import com.cherhy.util.extension.subscribe
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject
import kotlin.time.Duration.Companion.milliseconds

fun Application.configureBuyVideoConsumer() {
    val objectMapper by inject<ObjectMapper>()
    val purchaseVideoUseCase by inject<PurchaseVideoUseCase>()

    consumer.subscribe(PURCHASE_VIDEO_TOPIC)

    launch(IO) {
        while (true) {
            val buyVideoRecords = consumer.poll(100.milliseconds)

            buyVideoRecords.forEach {
                launch {
                    val purchaseVideoEvent = objectMapper.readValue<PurchaseVideoEvent>(it.value())

                    val userId = UserId.of(purchaseVideoEvent.userId)
                    val videoId = VideoId.of(purchaseVideoEvent.videoId)
                    val price = Price.of(purchaseVideoEvent.price)

                    purchaseVideoUseCase.execute(
                        videoId = videoId,
                        userId = userId,
                        price = price,
                    )
                }
            }
        }
    }
}