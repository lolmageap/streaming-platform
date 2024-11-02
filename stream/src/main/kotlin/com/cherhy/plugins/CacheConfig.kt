package com.cherhy.plugins

import com.cherhy.util.constant.CacheConst.REDIS_PORT
import com.cherhy.util.constant.CacheConst.REDIS_HOST
import com.cherhy.util.model.RedisConnection
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.lettuce.core.RedisClient

fun Application.configureCache() {
    val host = HoconApplicationConfig(ConfigFactory.load()).property(REDIS_HOST).getString()
    val port = HoconApplicationConfig(ConfigFactory.load()).property(REDIS_PORT).getString()
    val redisClient = RedisClient.create("redis://$host:$port")
    val connection = redisClient.connect()
    RedisConnection.client = redisClient

    environment.monitor.subscribe(ApplicationStopping) {
        connection.close()
        redisClient.shutdown()
    }
}