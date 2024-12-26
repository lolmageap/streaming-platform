package com.cherhy.plugins

import com.cherhy.common.util.KafkaConstant.BOOTSTRAP_SERVERS
import com.cherhy.common.util.KafkaConstant.Consumer.DEFAULT_GROUP_ID
import com.cherhy.common.util.KafkaConstant.Consumer.EARLIEST
import com.cherhy.common.util.KafkaConstant.Topic.BUY_VIDEO_TOPIC
import com.cherhy.event.BuyVideoEvent
import com.cherhy.usecase.BuyVideoUseCase
import com.cherhy.util.extension.poll
import com.cherhy.util.extension.subscribe
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.koin.ktor.ext.inject
import kotlin.time.Duration.Companion.milliseconds

fun Application.configureKafkaConsumer() {
    val objectMapper by inject<ObjectMapper>()
    val buyVideoUseCase by inject<BuyVideoUseCase>()

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

    consumer.subscribe(BUY_VIDEO_TOPIC)

    launch(IO) {
        while (true) {
            val buyVideoRecords = consumer.poll(100.milliseconds)

            buyVideoRecords.forEach {
                val buyVideoEvent = objectMapper.readValue<BuyVideoEvent>(it.value())
                buyVideoUseCase.execute(buyVideoEvent.userId, buyVideoEvent.videoId, buyVideoEvent.price)
            }
        }
    }
}

private const val BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers"
private const val GROUP_ID_CONFIG = "group.id"
private const val KEY_DESERIALIZER_CONFIG = "key.deserializer"
private const val VALUE_DESERIALIZER_CONFIG = "value.deserializer"
private const val AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset"
private const val MAX_POLL_RECORDS_CONFIG = "max.poll.records"