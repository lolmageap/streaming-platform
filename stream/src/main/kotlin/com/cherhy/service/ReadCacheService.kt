package com.cherhy.service

import com.cherhy.util.model.RedisConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReadCacheService {
    private val redisClient = RedisConnection.client
    private val commands = redisClient.connect().async()

    suspend fun read(
        key: String
    ): String =
        withContext(Dispatchers.IO) {
            commands.get(key).get()
        }
}