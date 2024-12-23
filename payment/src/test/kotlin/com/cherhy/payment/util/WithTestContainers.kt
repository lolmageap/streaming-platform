package com.cherhy.payment.util

import com.cherhy.payment.util.DataSource.Mongo
import com.cherhy.payment.util.DataSource.Postgres.Master
import com.cherhy.payment.util.DataSource.Postgres.Slave
import com.cherhy.payment.util.DataSource.Redis
import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports.Binding.bindPort
import org.flywaydb.core.Flyway
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer

interface WithTestContainers {
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
            registry.add(Master.Key.DATABASE_SOURCE_URL) { masterPostgres.masterR2dbcUrl }
            registry.add(Master.Key.DATABASE_SOURCE_USERNAME) { masterPostgres.username }
            registry.add(Master.Key.DATABASE_SOURCE_PASSWORD) { masterPostgres.password }

            registry.add(Slave.Key.DATABASE_SOURCE_URL) { slavePostgres.slaveR2dbcUrl }
            registry.add(Slave.Key.DATABASE_SOURCE_USERNAME) { slavePostgres.username }
            registry.add(Slave.Key.DATABASE_SOURCE_PASSWORD) { slavePostgres.password }
        }

        private val masterPostgres by lazy {
            PostgreSQLContainer<Nothing>(Master.Property.IMAGE).apply {
                withCreateContainerCmdModifier {
                    it.withName(Master.Property.NAME)
                        .hostConfig
                        ?.portBindings
                        ?.add(
                            PortBinding(
                                bindPort(Master.Property.BIND_PORT),
                                ExposedPort(Master.Property.PORT),
                            )
                        )
                }
                withExposedPorts(Master.Property.PORT)
                withDatabaseName(Master.Property.DATABASE_NAME)
                withUsername(Master.Property.USERNAME)
                withPassword(Master.Property.PASSWORD)
                withCommand(
                    Master.Command.POSTGRES,
                    Master.Command.ADD_OPTION, Master.Command.WAL_LEVEL,
                    Master.Command.ADD_OPTION, Master.Command.MAX_WAL_SENDERS,
                    Master.Command.ADD_OPTION, Master.Command.MAX_REPLICATION_SLOTS,
                    Master.Command.ADD_OPTION, Master.Command.HOT_STANDBY,
                )
            }
        }

        private val slavePostgres by lazy {
            PostgreSQLContainer<Nothing>(Slave.Property.IMAGE).apply {
                withCreateContainerCmdModifier {
                    it.withName(Slave.Property.NAME)
                        .hostConfig
                        ?.portBindings
                        ?.add(
                            PortBinding(
                                bindPort(Slave.Property.BIND_PORT),
                                ExposedPort(Slave.Property.PORT),
                            )
                        )
                }
                withExposedPorts(Slave.Property.PORT)
                withDatabaseName(Slave.Property.DATABASE_NAME)
                withUsername(Slave.Property.USERNAME)
                withPassword(Slave.Property.PASSWORD)
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

        private val mongo by lazy {
            GenericContainer<Nothing>(Mongo.IMAGE).apply {
                withCreateContainerCmdModifier {
                    it.withName(Mongo.NAME)
                        .hostConfig
                        ?.portBindings
                        ?.add(
                            PortBinding(
                                bindPort(Mongo.BIND_PORT),
                                ExposedPort(Mongo.PORT)
                            )
                        )
                }

                withExposedPorts(Mongo.PORT)
            }
        }

        private fun migrate() {
            Flyway.configure()
                .dataSource(
                    masterPostgres.jdbcUrl,
                    masterPostgres.username,
                    masterPostgres.password,
                )
                .load()
                .migrate()
        }

        private val activeTestContainers =
            listOf(masterPostgres, slavePostgres, redis, mongo)
    }
}

private val PostgreSQLContainer<Nothing>.masterR2dbcUrl
    get() = "r2dbc:postgresql://${this.host}:${this.getMappedPort(Master.Property.PORT)}/${this.databaseName}"

private val PostgreSQLContainer<Nothing>.slaveR2dbcUrl
    get() = "r2dbc:postgresql://${this.host}:${this.getMappedPort(Slave.Property.PORT)}/${this.databaseName}"