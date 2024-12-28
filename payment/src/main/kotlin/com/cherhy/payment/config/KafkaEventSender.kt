package com.cherhy.payment.config

import com.cherhy.common.util.KafkaConstant.Topic.PURCHASE_VIDEO_TOPIC
import com.cherhy.common.util.KafkaConstant.Topic.TEST_TOPIC
import com.cherhy.common.util.event.PaymentEvent
import com.cherhy.common.util.event.PurchaseVideoEvent
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventSender(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val objectMapper: ObjectMapper,
) {
    private val logger = KotlinLogging.logger {}

    suspend fun send(
        event: PaymentEvent,
    ) {
        val jsonToModel = objectMapper.writeValueAsString(event)
        try {
            kafkaTemplate.send(TEST_TOPIC, jsonToModel)
        } catch (e: Exception) {
            logger.error { "Error sending message to Kafka: ${e.message}" }
        }
        logger.info { "Message sent to Kafka: $jsonToModel" }
    }

    suspend fun send(
        event: PurchaseVideoEvent,
    ) {
        val jsonToModel = objectMapper.writeValueAsString(event)
        try {
            kafkaTemplate.send(PURCHASE_VIDEO_TOPIC, jsonToModel)
        } catch (e: Exception) {
            logger.error { "Error sending message to Kafka: ${e.message}" }
        }
        logger.info { "Message sent to Kafka: $jsonToModel" }
    }
}