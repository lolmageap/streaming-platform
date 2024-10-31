package com.cherhy.producer.config

import com.cherhy.producer.config.KafkaConstant.ALL
import com.cherhy.producer.config.KafkaConstant.BOOTSTRAP_SERVERS
import com.cherhy.producer.config.KafkaConstant.RETRIES
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {
    @Bean
    fun kafkaProducerFactory(): ProducerFactory<String, Any> =
        DefaultKafkaProducerFactory(
            mapOf(
                BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
                KEY_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java.name,
                VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java.name,
                ACKS_CONFIG to ALL,
                RETRIES_CONFIG to RETRIES,
            )
        )

    @Bean
    fun jsonKafkaTemplate() =
        KafkaTemplate(kafkaProducerFactory())
}