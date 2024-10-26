package com.cherhy.payment.util

object DataSource {
    object Redis {
        const val IMAGE = "redis:5.0.3-alpine"
        const val HOST = "localhost"
        const val NAME = "redis-test-container"
        const val PORT = 6379
        const val BIND_PORT = 16379
    }

    object Mongo {
        const val IMAGE = "mongo:latest"
        const val HOST = "localhost"
        const val NAME = "mongo-test-container"
        const val PORT = 27017
        const val BIND_PORT = 27717
    }

    object Postgres {
        object Master {
            object Property {
                const val IMAGE = "postgres:15.0"
                const val HOST = "localhost"
                const val NAME = "master-postgres-test-container"
                const val PORT = 5432
                const val BIND_PORT = 15432
                const val DATABASE_NAME = "cherhy"
                const val USERNAME = "postgres"
                const val PASSWORD = "1234"
                const val URL = "jdbc:postgresql://$HOST:$BIND_PORT/$DATABASE_NAME"
            }

            object Command {
                const val POSTGRES = "postgres"
                const val ADD_OPTION = "-c"
                const val WAL_LEVEL = "wal_level=replica"
                const val MAX_WAL_SENDERS = "max_wal_senders=3"
                const val MAX_REPLICATION_SLOTS = "max_replication_slots=3"
                const val HOT_STANDBY = "hot_standby=on"
            }

            object Key {
                const val DATABASE_SOURCE_URL = "r2dbc.master.url"
                const val DATABASE_SOURCE_USERNAME = "r2dbc.master.username"
                const val DATABASE_SOURCE_PASSWORD = "r2dbc.master.password"
            }
        }

        object Slave {
            object Property {
                const val IMAGE = "postgres:15.0"
                const val HOST = "localhost"
                const val NAME = "slave-postgres-test-container"
                const val PORT = 5432
                const val BIND_PORT = 15433
                const val DATABASE_NAME = "cherhy"
                const val USERNAME = "postgres"
                const val PASSWORD = "1234"
                const val URL = "jdbc:postgresql://$HOST:$BIND_PORT/$DATABASE_NAME"
            }

            object Key {
                const val DATABASE_SOURCE_URL = "r2dbc.slave.url"
                const val DATABASE_SOURCE_USERNAME = "r2dbc.slave.username"
                const val DATABASE_SOURCE_PASSWORD = "r2dbc.slave.password"
            }
        }
    }
}