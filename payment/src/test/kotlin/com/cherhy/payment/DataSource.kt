package com.cherhy.payment

object DataSource {
    object Redis {
        const val IMAGE = "redis:5.0.3-alpine"
        const val HOST = "localhost"
        const val NAME = "redis-test-container"
        const val PORT = 6379
        const val BIND_PORT = 16379
    }

    object Postgres {
        const val IMAGE = "postgres:17.0"
        const val HOST = "localhost"
        const val NAME = "postgres-test-container"
        const val PORT = 5432
        const val BIND_PORT = 15432
        const val DATABASE_NAME = "cherhy"
        const val USERNAME = "postgres"
        const val PASSWORD = "1234"
    }
}