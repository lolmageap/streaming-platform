package com.cherhy.payment.config

import com.cherhy.common.util.KafkaConstant.Topic.VIDEO_PURCHASE_FAILED_TOPIC
import com.cherhy.common.util.event.VideoPurchaseFailedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaEventConsumer(
    private val objectMapper: ObjectMapper,
) {
    val logger = KotlinLogging.logger {}

    @KafkaListener(topics = [VIDEO_PURCHASE_FAILED_TOPIC])
    fun listenFailedPurchaseVideoEvent(
        eventToString: String,
    ) {
        val event = objectMapper.readValue(eventToString, VideoPurchaseFailedEvent::class.java)
        logger.info { "Received message from Kafka: $event" }
    }
}