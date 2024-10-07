package com.cherhy.util.model

import io.lettuce.core.RedisClient

object RedisConnection {
    lateinit var client: RedisClient
}