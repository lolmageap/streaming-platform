package com.cherhy.payment

import com.cherhy.payment.TestContainers.DataSource.Postgres
import com.cherhy.payment.TestContainers.DataSource.Redis
import com.cherhy.payment.TestContainers.postgresContainer
import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
import org.flywaydb.core.Flyway
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer

object TestContainers {
    val redisContainer by lazy {
        GenericContainer<Nothing>(Redis.IMAGE).apply {
            withCreateContainerCmdModifier {
                it.withName(Redis.NAME)
                    .hostConfig
                    ?.portBindings
                    ?.add(
                        PortBinding(
                            Ports.Binding.bindPort(Redis.BIND_PORT),
                            ExposedPort(Redis.PORT)
                        )
                    )
            }

            withExposedPorts(Redis.PORT)
        }
    }

    val postgresContainer by lazy {
        PostgreSQLContainer<Nothing>(Postgres.IMAGE).apply {
            withCreateContainerCmdModifier {
                it.withName(Postgres.NAME)
                    .hostConfig
                    ?.portBindings
                    ?.add(
                        PortBinding(
                            Ports.Binding.bindPort(Postgres.BIND_PORT),
                            ExposedPort(Postgres.PORT)
                        )
                    )
            }
            withExposedPorts(Postgres.PORT)
            withDatabaseName(Postgres.DATABASE_NAME)
            withUsername(Postgres.USERNAME)
            withPassword(Postgres.PASSWORD)
        }
    }

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
}

object FlywayConfigurer {
    val flyway: Flyway = Flyway.configure()
        .dataSource(
            postgresContainer.jdbcUrl,
            postgresContainer.username,
            postgresContainer.password,
        )
        .load()
}