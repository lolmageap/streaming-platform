package com.cherhy.plugins

import com.cherhy.common.util.KafkaConstant.BOOTSTRAP_SERVERS
import com.cherhy.common.util.KafkaConstant.Consumer.DEFAULT_GROUP_ID
import com.cherhy.common.util.KafkaConstant.Consumer.EARLIEST
import com.cherhy.common.util.KafkaConstant.Topic.TEST_TOPIC
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import io.ktor.server.application.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

fun Application.configureKafkaConsumer() {
    val consumer = KafkaConsumer<String, String>(
        mapOf(
            BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
            GROUP_ID_CONFIG to DEFAULT_GROUP_ID,
            KEY_DESERIALIZER_CONFIG to StringDeserializer::class.java.name,
            VALUE_DESERIALIZER_CONFIG to StringDeserializer::class.java.name,
            AUTO_OFFSET_RESET_CONFIG to EARLIEST,
            MAX_POLL_RECORDS_CONFIG to 10,
        )
    )

    consumer.subscribe(
        listOf(
            TEST_TOPIC,
        )
    )

    while (true) {
        val records = consumer.poll(ONE_HUNDRED_MILLIS)

        // TODO : records 처리 로직 구현
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

private val ONE_HUNDRED_MILLIS = Duration.ofMillis(100)
