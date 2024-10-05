package com.cherhy.plugins

import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import io.ktor.server.application.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

fun Application.configureKafka() {
    val consumer = KafkaConsumer<String, String>(
        mapOf(
            BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
            GROUP_ID_CONFIG to GROUP_ID,
            KEY_DESERIALIZER_CONFIG to StringDeserializer::class.java.name,
            VALUE_DESERIALIZER_CONFIG to StringDeserializer::class.java.name,
            AUTO_OFFSET_RESET_CONFIG to EARLIEST,
            MAX_POLL_RECORDS_CONFIG to 10,
        )
    )

    consumer.subscribe(
        listOf(
            TOPIC,
        )
    )

    while (true) {
        val records = consumer.poll(ONE_HUNDRED_MILLIS)

        records.forEach {
            println("Consumed record: ${it.value()}")
        }
    }
}

private const val BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers"
private const val GROUP_ID_CONFIG = "group.id"
private const val KEY_DESERIALIZER_CONFIG = "key.deserializer"
private const val VALUE_DESERIALIZER_CONFIG = "value.deserializer"
private const val AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset"
private const val MAX_POLL_RECORDS_CONFIG = "max.poll.records"

private const val EARLIEST = "earliest"
private const val BOOTSTRAP_SERVERS = "localhost:9092"
private const val GROUP_ID = "test-group"
private const val TOPIC = "test-topic"
private val ONE_HUNDRED_MILLIS = Duration.ofMillis(100)
