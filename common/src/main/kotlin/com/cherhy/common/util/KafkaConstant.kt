package com.cherhy.common.util

object KafkaConstant {
    const val BOOTSTRAP_SERVERS = "localhost:9092"

    object Consumer {
        const val DEFAULT_GROUP_ID = "default-group-id"
        const val EARLIEST = "earliest"
    }

    object Producer {
        const val ALL = "all"
        const val RETRIES = "100"
    }

    object Topic {
        const val TEST_TOPIC = "test-topic"
    }
}