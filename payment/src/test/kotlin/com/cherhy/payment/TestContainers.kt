package com.cherhy.payment

import com.cherhy.payment.DataSource.Postgres
import com.cherhy.payment.DataSource.Redis
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
}

object FlywayConfigurer {
    val flyway: Flyway = Flyway.configure()
        .dataSource(
            TestContainers.postgresContainer.jdbcUrl,
            TestContainers.postgresContainer.username,
            TestContainers.postgresContainer.password,
        )
        .load()
}