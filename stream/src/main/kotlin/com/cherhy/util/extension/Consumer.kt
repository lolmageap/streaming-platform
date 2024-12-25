package com.cherhy.util.extension

import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import kotlin.time.Duration
import kotlin.time.toJavaDuration

fun <T, R> KafkaConsumer<T, R>.poll(duration: Duration): ConsumerRecords<T, R> = poll(duration.toJavaDuration())
fun <T, R> KafkaConsumer<T, R>.subscribe(vararg topics: String) = subscribe(topics.toList())