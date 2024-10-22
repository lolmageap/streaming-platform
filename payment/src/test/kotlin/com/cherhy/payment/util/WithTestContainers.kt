package com.cherhy.payment.util

import com.cherhy.payment.util.DataSource.Postgres
import com.cherhy.payment.util.DataSource.Postgres.Command.ADD_OPTION
import com.cherhy.payment.util.DataSource.Postgres.Command.HOT_STANDBY
import com.cherhy.payment.util.DataSource.Postgres.Command.MAX_REPLICATION_SLOTS
import com.cherhy.payment.util.DataSource.Postgres.Command.MAX_WAL_SENDERS
import com.cherhy.payment.util.DataSource.Postgres.Command.POSTGRES
import com.cherhy.payment.util.DataSource.Postgres.Command.WAL_LEVEL
import com.cherhy.payment.util.DataSource.Postgres.Key.DATABASE_SOURCE_PASSWORD
import com.cherhy.payment.util.DataSource.Postgres.Key.DATABASE_SOURCE_URL
import com.cherhy.payment.util.DataSource.Postgres.Key.DATABASE_SOURCE_USERNAME
import com.cherhy.payment.util.DataSource.Redis
import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports.Binding.bindPort
import org.flywaydb.core.Flyway
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer

internal interface WithTestContainers {
    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun initTestContainers(
            registry: DynamicPropertyRegistry,
        ) {
            activeTestContainers.parallelStream().forEach { it.start() }
            injectProperties(registry)
            migrate()
        }

        private fun injectProperties(
            registry: DynamicPropertyRegistry,
        ) {
            registry.add(DATABASE_SOURCE_URL) { postgres.jdbcUrl }
            registry.add(DATABASE_SOURCE_USERNAME) { postgres.username }
            registry.add(DATABASE_SOURCE_PASSWORD) { postgres.password }
        }

        private val postgres by lazy {
            PostgreSQLContainer<Nothing>(Postgres.Property.IMAGE).apply {
                withCreateContainerCmdModifier {
                    it.withName(Postgres.Property.NAME)
                        .hostConfig
                        ?.portBindings
                        ?.add(
                            PortBinding(
                                bindPort(Postgres.Property.BIND_PORT),
                                ExposedPort(Postgres.Property.PORT),
                            )
                        )
                }
                withExposedPorts(Postgres.Property.PORT)
                withDatabaseName(Postgres.Property.DATABASE_NAME)
                withUsername(Postgres.Property.USERNAME)
                withPassword(Postgres.Property.PASSWORD)
                withCommand(
                    POSTGRES,
                    ADD_OPTION, WAL_LEVEL,
                    ADD_OPTION, MAX_WAL_SENDERS,
                    ADD_OPTION, MAX_REPLICATION_SLOTS,
                    ADD_OPTION, HOT_STANDBY,
                )
            }
        }

        private val redis by lazy {
            GenericContainer<Nothing>(Redis.IMAGE).apply {
                withCreateContainerCmdModifier {
                    it.withName(Redis.NAME)
                        .hostConfig
                        ?.portBindings
                        ?.add(
                            PortBinding(
                                bindPort(Redis.BIND_PORT),
                                ExposedPort(Redis.PORT)
                            )
                        )
                }

                withExposedPorts(Redis.PORT)
            }
        }

        private fun migrate() {
            Flyway.configure()
                .dataSource(
                    postgres.jdbcUrl,
                    postgres.username,
                    postgres.password,
                )
                .load()
                .migrate()
        }

        private val activeTestContainers = listOf(postgres, redis)
    }
}