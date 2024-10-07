package com.cherhy.plugins

import com.cherhy.util.constant.CacheConst.REDIS_PORT
import com.cherhy.util.constant.CacheConst.REDIS_URL
import com.cherhy.util.model.RedisConnection
import io.ktor.server.application.*
import io.lettuce.core.RedisClient

fun Application.configureCache() {
    val url = environment.config.property(REDIS_URL).getString()
    val port = environment.config.property(REDIS_PORT).getString()
    val redisClient = RedisClient.create("redis://$url:$port")
    val connection = redisClient.connect()
    RedisConnection.client = redisClient

    environment.monitor.subscribe(ApplicationStopping) {
        connection.close()
        redisClient.shutdown()
    }
}