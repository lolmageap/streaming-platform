package com.cherhy.plugins

import com.cherhy.common.util.KafkaConstant
import com.cherhy.common.util.event.VideoPurchaseFailedEvent
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.clients.producer.ProducerRecord

private val publisher = KafkaProducer<String, String>(
    mapOf(
        BOOTSTRAP_SERVERS_CONFIG to KafkaConstant.BOOTSTRAP_SERVERS,
        KEY_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java.name,
        VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java.name,
        ACKS_CONFIG to KafkaConstant.Producer.ALL,
        RETRIES_CONFIG to KafkaConstant.Producer.RETRIES,
    )
)

class KafkaPublisher(
    private val objectMapper: ObjectMapper,
) {
    fun publish(
        videoPurchaseFailedEvent: VideoPurchaseFailedEvent,
    ) {
        val serializedEvent = objectMapper.writeValueAsString(videoPurchaseFailedEvent)
        publisher.send(
            ProducerRecord(
                KafkaConstant.Topic.VIDEO_PURCHASE_FAILED_TOPIC,
                serializedEvent,
            )
        )
    }
}