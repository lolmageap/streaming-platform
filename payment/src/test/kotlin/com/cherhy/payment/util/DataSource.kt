package com.cherhy.payment.util

object DataSource {
    object Redis {
        const val IMAGE = "redis:5.0.3-alpine"
        const val HOST = "localhost"
        const val NAME = "redis-test-container"
        const val PORT = 6379
        const val BIND_PORT = 16379
    }

    object Postgres {
        object Property {
            const val IMAGE = "postgres:15.0"
            const val HOST = "localhost"
            const val NAME = "postgres-test-container"
            const val PORT = 5432
            const val BIND_PORT = 15432
            const val DATABASE_NAME = "cherhy"
            const val USERNAME = "postgres"
            const val PASSWORD = "1234"
            const val URL = "jdbc:postgresql://$HOST:$BIND_PORT/$DATABASE_NAME"
        }

        object Command{
            const val POSTGRES = "postgres"
            const val ADD_OPTION = "-c"
            const val WAL_LEVEL = "wal_level=replica"
            const val MAX_WAL_SENDERS = "max_wal_senders=3"
            const val MAX_REPLICATION_SLOTS = "max_replication_slots=3"
            const val HOT_STANDBY = "hot_standby=on"
        }

        object Key {
            const val DATABASE_SOURCE_URL = "spring.datasource.master.url"
            const val DATABASE_SOURCE_USERNAME = "spring.datasource.master.username"
            const val DATABASE_SOURCE_PASSWORD = "spring.datasource.master.password"
        }
    }
}