package com.cherhy.service

import com.cherhy.util.model.RedisConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WriteCacheService {
    private val redisClient = RedisConnection.client
    private val commands = redisClient.connect().async()

    suspend fun write(
        key: String,
        value: String
    ) {
        withContext(Dispatchers.IO) {
            commands.set(key, value)
        }
    }
}