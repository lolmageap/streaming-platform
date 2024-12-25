package com.cherhy

import com.cherhy.plugins.configureCache
import com.cherhy.plugins.configureDependencyInjection
import com.cherhy.plugins.configureKafkaConsumer
import com.cherhy.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureDependencyInjection()
    configureCache()
    configureKafkaConsumer()
}